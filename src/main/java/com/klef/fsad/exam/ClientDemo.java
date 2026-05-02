package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;
 class ClientDemo {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("SessionFactory creation failed: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }
    public static void insertTickets() {
        System.out.println("\n========================================");
        System.out.println("  I. INSERTING TICKET RECORDS");
        System.out.println("========================================");

        Ticket t1 = new Ticket(
                "Login Page Bug",
                LocalDate.of(2024, 1, 10),
                "Open",
                "Bug",
                "High",
                "Alice Johnson"
        );

        Ticket t2 = new Ticket(
                "Dashboard UI Enhancement",
                LocalDate.of(2024, 1, 15),
                "In Progress",
                "Feature",
                "Medium",
                "Bob Smith"
        );

        Ticket t3 = new Ticket(
                "Payment Gateway Failure",
                LocalDate.of(2024, 1, 18),
                "Open",
                "Bug",
                "Critical",
                "Carol White"
        );

        Ticket t4 = new Ticket(
                "Add Dark Mode",
                LocalDate.of(2024, 1, 20),
                "Pending",
                "Feature",
                "Low",
                "David Brown"
        );

        Ticket t5 = new Ticket(
                "Database Timeout Issue",
                LocalDate.of(2024, 1, 22),
                "Open",
                "Bug",
                "High",
                "Eva Green"
        );

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.persist(t1);
            session.persist(t2);
            session.persist(t3);
            session.persist(t4);
            session.persist(t5);

            tx.commit();

            System.out.println("5 Ticket records inserted successfully!");
            System.out.println("  -> " + t1);
            System.out.println("  -> " + t2);
            System.out.println("  -> " + t3);
            System.out.println("  -> " + t4);
            System.out.println("  -> " + t5);

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Insert failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void updateTicketByHQL(int ticketId, String newName, String newStatus) {
        System.out.println("\n========================================");
        System.out.println("  II. UPDATING TICKET via HQL");
        System.out.println("========================================");
        System.out.printf("  Target ID   : %d%n", ticketId);
        System.out.printf("  New Name    : %s%n", newName);
        System.out.printf("  New Status  : %s%n", newStatus);
        String hql = "UPDATE Ticket t SET t.name = ?1, t.status = ?2 WHERE t.id = ?3";

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Query<?> query = session.createMutationQuery(hql);
            query.setParameter(1, newName);     
            query.setParameter(2, newStatus);   
            query.setParameter(3, ticketId);    

            int rowsAffected = query.executeUpdate();
            tx.commit();

            if (rowsAffected > 0) {
                System.out.println("  Update successful! Rows affected: " + rowsAffected);
            } else {
                System.out.println("  No record found with ID = " + ticketId);
            }

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Update failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void displayAllTickets() {
        System.out.println("\n========================================");
        System.out.println("  ALL TICKET RECORDS (HQL SELECT)");
        System.out.println("========================================");

        String hql = "FROM Ticket t ORDER BY t.id";

        try (Session session = sessionFactory.openSession()) {
            Query<Ticket> query = session.createQuery(hql, Ticket.class);
            List<Ticket> tickets = query.getResultList();

            if (tickets.isEmpty()) {
                System.out.println("  No records found.");
            } else {
                for (Ticket t : tickets) {
                    System.out.println("  " + t);
                }
            }
        } catch (Exception e) {
            System.err.println("Fetch failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   Hibernate HQL Demo – Ticket Entity     ║");
        System.out.println("║   Database : fsadendexam                 ║");
        System.out.println("║   Package  : com.klef.fsad.exam          ║");
        System.out.println("╚══════════════════════════════════════════╝");
        insertTickets();
        System.out.println("\n--- Records BEFORE update ---");
        displayAllTickets();
        updateTicketByHQL(1, "Login Page Bug - RESOLVED", "Closed");
        updateTicketByHQL(3, "Payment Gateway Failure - IN REVIEW", "In Progress");
        System.out.println("\n--- Records AFTER update ---");
        displayAllTickets();
        sessionFactory.close();
        System.out.println("\n✔  ClientDemo execution completed successfully.");
    }
}
