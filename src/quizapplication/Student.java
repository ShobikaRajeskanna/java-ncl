package com.example.quizapplication;



import java.util.Date;

public class Student {

    private final String firstName;
    private final String lastName;
    private final Date birthDate;
    boolean passedQuiz;
    private StudentStats stats;



    public Student(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        if (firstName == null)
            throw new IllegalArgumentException("First name cannot be null ");
        this.lastName = lastName;
        if (lastName == null)
            throw new IllegalArgumentException("First name cannot be null ");
        this.birthDate = birthDate;
        if (birthDate == null)
            throw new IllegalArgumentException("First name cannot be null ");
        this.stats = new StudentStats();

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public boolean equals(Object samestudent) {
        if (this == samestudent)
            return true; //same instance,therefore it is equal
        if(!(samestudent instanceof Student))
            return false; //not same type,therefore it is not equal
        Student student = (Student) samestudent;
        //compare the fields for equality
        return firstName == student.getFirstName() &&
                lastName.equals(student.getLastName()) &&
                        birthDate.equals(student.getBirthDate());
    }
    @Override
    //implementing hashcode contracts with equals method and check the null
    public int hashCode() {
        int result = 17;
        result = 31 * result + (firstName == null? 0 : firstName.hashCode());
        result = 31 * result + (lastName == null? 0 : lastName.hashCode());
        result = 31 * result + (birthDate== null? 0 : birthDate.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + String.valueOf(firstName) + '\'' +
                ", lastName='" + String.valueOf(lastName) + '\'' +
                ", birthDate=" + String.valueOf(birthDate) +
                '}';
    }

    public void setPassedQuiz(boolean passedQuiz) {
        this.passedQuiz=passedQuiz;

    }

    public boolean hasPassedQuiz() {
        return passedQuiz;
    }
    public StudentStats getStats() {
        return stats;
    }
}
