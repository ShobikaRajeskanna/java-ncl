package com.example.UnitTesting;

import com.example.quizapplication.StudentStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentStatsTest {

    private StudentStats studentStats;

    @BeforeEach
    public void setUp() {
        // Initialize a new instance of StudentStats before each test
        studentStats = new StudentStats();
    }

    // Test initial state of StudentStats
    @Test
    public void testInitialState() {
        assertEquals("TBD", studentStats.getFinalVerdict(), "Initial final verdict should be 'TBD'.");
        assertEquals(0, studentStats.getAttempts(), "Initial number of attempts should be 0.");
        assertEquals(0, studentStats.getRevisionAttempts(), "Initial number of revision attempts should be 0.");
        assertFalse(studentStats.hasFinalVerdict(), "Initial final verdict should be 'TBD', so hasFinalVerdict() should return false.");
    }

    // Test adding a quiz attempt that results in a pass
    @Test
    public void testAddQuizAttempt_Pass() {
        studentStats.addQuizAttempt(0.6);  // This should mark the student as passed

        assertEquals(1, studentStats.getAttempts(), "Attempts should be incremented to 1.");
        assertEquals("PASS", studentStats.getFinalVerdict(), "Final verdict should be 'PASS' after a passing score.");
        assertTrue(studentStats.hasFinalVerdict(), "hasFinalVerdict() should return true once the final verdict is set.");
    }

    // Test adding a quiz attempt that results in a fail
    @Test
    public void testAddQuizAttempt_FailAfterTwoAttempts() {
        studentStats.addQuizAttempt(0.4);  // First attempt (fail)
        studentStats.addQuizAttempt(0.3);  // Second attempt (fail)

        studentStats.addRevisionAttempt(0.4);  // First revision attempt
        studentStats.addRevisionAttempt(0.3);  // Second revision attempt

        assertEquals(2, studentStats.getAttempts(), "Attempts should be incremented to 2 after two attempts.");
        assertEquals(2, studentStats.getRevisionAttempts(), "Revision attempts should be incremented to 2 after two revisions.");
        assertEquals("FAIL", studentStats.getFinalVerdict(), "Final verdict should be 'FAIL' after two attempts and two revisions without passing.");
        assertTrue(studentStats.hasFinalVerdict(), "hasFinalVerdict() should return true once the final verdict is set.");
    }

    // Test adding a revision attempt without affecting the final verdict
    @Test
    public void testAddRevisionAttemptWithoutAffectingFinalVerdict() {
        studentStats.addRevisionAttempt(0.3);
        studentStats.addRevisionAttempt(0.4);

        assertEquals(0, studentStats.getAttempts(), "No quiz attempts should have been added.");
        assertEquals(2, studentStats.getRevisionAttempts(), "Revision attempts should be incremented to 2.");
        assertEquals("TBD", studentStats.getFinalVerdict(), "Final verdict should still be 'TBD' as there have been no passing scores in quiz attempts.");
    }

    // Test quiz history
    @Test
    public void testQuizHistory() {
        studentStats.addQuizAttempt(0.4);  // First quiz attempt
        studentStats.addQuizAttempt(0.3);  // Second quiz attempt
        studentStats.addRevisionAttempt(0.5);  // First revision attempt
        studentStats.addRevisionAttempt(0.6);  // Second revision attempt

        String expectedHistory = "Quiz Scores: [0.4, 0.3]\n" +
                "Revision Scores: [0.5, 0.6]\n" +
                "Attempts: 2\n" +
                "Revision Attempts: 2\n";

        assertEquals(expectedHistory, studentStats.getQuizHistory(), "Quiz history should be correctly generated based on the quiz and revision attempts.");
    }
}
