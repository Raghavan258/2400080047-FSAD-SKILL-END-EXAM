package com.klef.fsad.exam;

import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int id;

    @Column(name = "ticket_name", nullable = false, length = 100)
    private String name;

    @Column(name = "ticket_date", nullable = false)
    private LocalDate date;

    @Column(name = "ticket_status", nullable = false, length = 50)
    private String status;

    @Column(name = "ticket_category", length = 50)
    private String category;

    @Column(name = "ticket_priority", length = 20)
    private String priority;

    @Column(name = "assigned_to", length = 100)
    private String assignedTo;
    public Ticket() {
    }
    public Ticket(String name, LocalDate date, String status,
                  String category, String priority, String assignedTo) {
        this.name       = name;
        this.date       = date;
        this.status     = status;
        this.category   = category;
        this.priority   = priority;
        this.assignedTo = assignedTo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
    @Override
    public String toString() {
        return "Ticket {" +
               "  id="         + id         +
               ", name='"      + name       + '\'' +
               ", date="       + date       +
               ", status='"    + status     + '\'' +
               ", category='"  + category   + '\'' +
               ", priority='"  + priority   + '\'' +
               ", assignedTo='"+ assignedTo + '\'' +
               " }";
    }
}
