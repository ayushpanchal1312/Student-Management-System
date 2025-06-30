
# 🎓 Student Management System

## 📌 About the Project
This project is a simple **Student Management System** built with Java and JavaFX. It allows users to:
- Add student details
- Update student information
- View the student list
- Admin login functionality

---

## ⚙️ How to Run

1. Import the project into your Java IDE (IntelliJ/Eclipse/NetBeans).
3. Run the `Main` class to start the application.

---

## 🗄️ Technologies Used
- Java
- JavaFX
- PgAdmin4

---

## 📂 Database Setup

### **Database Design**

#### **Students Table**

Database Name:Student_management

| Column Name | Data Type              | Constraints                |
|--------------|-----------------------|-----------------------------|
| id           | `integer`             | Primary Key, Auto Increment |
| name         | `character varying(100)` | Not Null                   |
| roll_no      | `character varying(50)`  | Not Null                   |
| department   | `character varying(100)` | Not Null                   |
| email        | `character varying(100)` | unique                     |
| phone        | `character varying(15)`  | Not Null                   |

---

#### **Admin Table**

| Column Name | Data Type              | Constraints                |
|--------------|-----------------------|-----------------------------|
| id           | `integer`             | Primary Key, Auto Increment |
| username     | `character varying(50)`  | Not Null                   |
| password     | `character varying(255)` | Not Null                   |

---

### **SQL to Create Tables**

```sql
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    roll_no VARCHAR(50) NOT NULL,
    department VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL
);

CREATE TABLE admin (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);
```

---

### **Reset Serial ID (Optional)**

If you delete records and want to reset the `id` counter:

```sql
ALTER SEQUENCE students_id_seq RESTART WITH 1;
ALTER SEQUENCE admin_id_seq RESTART WITH 1;
```

---

## 👀 Demo

Screenshots and a video of the working project are included in the `demo` folder.

---

## ✅ Deliverables for Internship Submission

- ✅ Complete Java code (on GitHub or Google Drive)
- ✅ Screenshots/video of the working project
- ✅ SQL file for database setup
- ✅ This README.md file
- ✅ Project Completion Form (Google Form)
