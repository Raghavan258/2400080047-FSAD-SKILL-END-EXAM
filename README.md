# Hibernate HQL – Ticket Entity Project

## Package : `com.klef.fsad.exam`
## Database : `fsadendexam`

---

## Project Structure

```
hibernate-hql-ticket/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── com/klef/fsad/exam/
        │       ├── Ticket.java        ← Entity class
        │       └── ClientDemo.java    ← HQL operations demo
        └── resources/
            └── hibernate.cfg.xml      ← Hibernate configuration
```

---

## Entity – `Ticket`

| Field        | Column          | Type       | Notes              |
|--------------|-----------------|------------|--------------------|
| id           | ticket_id       | INT (PK)   | Auto-generated     |
| name         | ticket_name     | VARCHAR    | Not null           |
| date         | ticket_date     | DATE       | Not null           |
| status       | ticket_status   | VARCHAR    | Not null           |
| category     | ticket_category | VARCHAR    |                    |
| priority     | ticket_priority | VARCHAR    |                    |
| assignedTo   | assigned_to     | VARCHAR    |                    |

---

## HQL Operations in `ClientDemo`

### I. Insert Records (Persistent Object)
```java
session.persist(ticket);   // inserts via Hibernate persistent object
```

### II. Update Name & Status by ID (HQL Positional Parameters)
```java
String hql = "UPDATE Ticket t SET t.name = ?1, t.status = ?2 WHERE t.id = ?3";
Query<?> query = session.createMutationQuery(hql);
query.setParameter(1, newName);
query.setParameter(2, newStatus);
query.setParameter(3, ticketId);
query.executeUpdate();
```

---

## Prerequisites

- Java 17+
- Maven 3.6+
- MySQL 8.x running on `localhost:3306`

---

## Setup & Run

### 1. Create the MySQL database (optional – auto-created via URL param)
```sql
CREATE DATABASE IF NOT EXISTS fsadendexam;
```

### 2. Configure DB credentials
Edit `src/main/resources/hibernate.cfg.xml`:
```xml
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">root</property>
```

### 3. Build the project
```bash
mvn clean compile
```

### 4. Run the demo
```bash
mvn exec:java -Dexec.mainClass="com.klef.fsad.exam.ClientDemo"
```

---

## Expected Output

```
╔══════════════════════════════════════════╗
║   Hibernate HQL Demo – Ticket Entity     ║
║   Database : fsadendexam                 ║
║   Package  : com.klef.fsad.exam          ║
╚══════════════════════════════════════════╝

========================================
  I. INSERTING TICKET RECORDS
========================================
5 Ticket records inserted successfully!

--- Records BEFORE update ---
  Ticket { id=1, name='Login Page Bug', status='Open', ... }
  Ticket { id=2, name='Dashboard UI Enhancement', status='In Progress', ... }
  ...

========================================
  II. UPDATING TICKET via HQL
========================================
  Update successful! Rows affected: 1

--- Records AFTER update ---
  Ticket { id=1, name='Login Page Bug - RESOLVED', status='Closed', ... }
  ...

✔  ClientDemo execution completed successfully.
```

---

## Dependencies

| Dependency              | Version     |
|-------------------------|-------------|
| hibernate-core          | 6.4.4.Final |
| mysql-connector-java    | 8.0.33      |
| Java                    | 17          |
