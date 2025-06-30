package org.salonmaster.salonmaster.studnet_managemnt.Student_DATA;

/**
 * Represents a student entity with attributes such as ID, name, roll number, department, email, phone, and marks.
 */
public class Student {

    /** Unique identifier for the student (Primary Key). */
    private int id;

    /** Name of the student. */
    private String name;

    /** Roll number of the student. */
    private String roll_no;

    /** Department the student belongs to. */
    private String department;

    /** Email address of the student. */
    private String email;

    /** Phone number of the student. */
    private String phone;

    /** Marks obtained by the student. */
    private double marks;

    /**
     * Constructs a new {@code Student} object with the provided details.
     *
     * @param id         The unique ID of the student.
     * @param name       The name of the student.
     * @param roll_no    The roll number of the student.
     * @param department The department of the student.
     * @param email      The email address of the student.
     * @param phone      The phone number of the student.
     * @param marks      The marks obtained by the student.
     */
    public Student(int id, String name, String roll_no, String department, String email, String phone, double marks) {
        this.id = id;
        this.name = name;
        this.roll_no = roll_no;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.marks = marks;
    }

    /**
     * Returns the ID of the student.
     *
     * @return The student ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the student.
     *
     * @param id The student ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     *
     * @param name The student's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the roll number of the student.
     *
     * @return The student's roll number.
     */
    public String getRoll_no() {
        return roll_no;
    }

    /**
     * Sets the roll number of the student.
     *
     * @param roll_no The student's roll number.
     */
    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    /**
     * Returns the department of the student.
     *
     * @return The student's department.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department of the student.
     *
     * @param department The student's department.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Returns the email address of the student.
     *
     * @return The student's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the student.
     *
     * @param email The student's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the phone number of the student.
     *
     * @return The student's phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the student.
     *
     * @param phone The student's phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the marks obtained by the student.
     *
     * @return The student's marks.
     */
    public double getMarks() {
        return marks;
    }
    /**
     * Sets the marks obtained by the student.
     *
     * @param marks The student's marks.
     */
    public void setMarks(double marks) {
        this.marks = marks;
    }
}
