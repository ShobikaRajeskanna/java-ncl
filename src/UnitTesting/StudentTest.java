package com.example.UnitTesting;

import com.example.quizapplication.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    private Student student1;
    private Student student2;
    private Student student3;
    private Date birthDate1;
    private Date birthDate2;

    @BeforeEach
    public void setUp() {
        // Create birthDate1 using Calendar
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2000, Calendar.NOVEMBER, 18); // November 18, 2000
        birthDate1 = calendar1.getTime();

        // Create birthDate2 using Calendar
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(1998, Calendar.JUNE, 25); // June 25, 1998
        birthDate2 = calendar2.getTime();

        // Initialize students
        student1 = new Student("Shobi", "Raj", birthDate1);
        student2 = new Student("Gowri", "Sundhar", birthDate2);
        student3 = new Student("Shobi", "Raj", birthDate1); // Same as student1
    }

    // Test constructor with valid input
    @Test
    public void testConstructorValidInput() {
        assertNotNull(student1, "Student should be successfully created with valid input.");
        assertEquals("Shobi", student1.getFirstName(), "First name should match.");
        assertEquals("Raj", student1.getLastName(), "Last name should match.");
        assertEquals(birthDate1, student1.getBirthDate(), "Birth date should match.");
    }

    // Test equals method for same instance
    @Test
    public void testEqualsSameInstance() {
        assertTrue(student1.equals(student1), "A student should be equal to itself.");
    }

    // Test equals method for different instances with same values
    @Test
    public void testEqualsSameValues() {
        assertTrue(student1.equals(student3), "Students with the same values should be equal.");
    }

    // Test equals method for different instances with different values
    @Test
    public void testEqualsDifferentValues() {
        assertFalse(student1.equals(student2), "Students with different values should not be equal.");
    }

    // Test hashCode contract with equals
    @Test
    public void testHashCodeEqualObjects() {
        assertEquals(student1.hashCode(), student3.hashCode(), "Equal students should have the same hashCode.");
    }

    // Test hashCode for unequal objects
    @Test
    public void testHashCodeNotEqualObjects() {
        assertNotEquals(student1.hashCode(), student2.hashCode(), "Unequal students should not have the same hashCode.");
    }

    // Test toString method
    @Test
    public void testToString() {
        String expectedString = "Student{firstName='Shobi', lastName='Raj', birthDate=" + birthDate1 + "}";
        assertEquals(expectedString, student1.toString(), "The toString method should return the expected string.");
    }

    // Test setPassedQuiz and hasPassedQuiz methods
    @Test
    public void testPassedQuiz() {
        // Initially, student should not have passed the quiz
        assertFalse(student1.hasPassedQuiz(), "The student should not have passed the quiz initially.");

        // Set passedQuiz to true
        student1.setPassedQuiz(true);
        assertTrue(student1.hasPassedQuiz(), "The student should have passed the quiz after setting passedQuiz to true.");
    }
}
