//package com.example.UnitTesting;

//import com.example.question.FreeResponse;
//import com.example.question.Questions;
//import com.example.quizapplication.RevisionQuiz;
//import com.example.quizapplication.Student;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//public class RevisionQuizTest {
//
//    private RevisionQuiz revisionQuiz;
//    private Student student1;
//    private Student student2;
//    private Questions question1;
//    private Questions question2;
//    private Questions question3;
//
//    @BeforeEach
//    public void setUp() {
//        // Mock some students
//
//        student1 = new Student("Shobi","Raj", Date 14-06-2001);
//       // student2 = new Student("Jane Smith");
//
//        // Mock some questions
//        question1 = new FreeResponse("What is Java?", "A programming language");
//        question2 = new FreeResponse("What is OOP?", "Object-oriented programming");
//        question3 = new FreeResponse("What is inheritance?", "A feature of OOP");
//
//        // Create a list of wrong questions
//        List<Questions> wrongQuestions = new ArrayList<>(Arrays.asList(question1, question2, question3));
//
//        // Initialize the RevisionQuiz
//        revisionQuiz = new RevisionQuiz(wrongQuestions);
//    }
//
//    @Test
//    public void testGetQuestionsText() {
//        List<Questions> questions = revisionQuiz.getQuestionsText(student1);
//        assertEquals(3, questions.size(), "The number of questions should be 3.");
//        assertTrue(questions.contains(question1), "The question list should contain question1.");
//        assertTrue(questions.contains(question2), "The question list should contain question2.");
//        assertTrue(questions.contains(question3), "The question list should contain question3.");
//    }
//
//    @Test
//    public void testShuffleQuestions() {
//        List<Questions> originalOrder = new ArrayList<>(revisionQuiz.getQuestionsText(student1));
//        revisionQuiz.shuffleQuestions();
//        List<Questions> shuffledOrder = revisionQuiz.getQuestionsText(student1);
//
//        // Verify that shuffling changes the order, but still contains the same elements
//        assertNotEquals(originalOrder, shuffledOrder, "The order of questions should be shuffled.");
//        assertEquals(originalOrder.size(), shuffledOrder.size(), "The number of questions should remain the same after shuffling.");
//        assertTrue(shuffledOrder.containsAll(originalOrder), "Shuffled list should contain all original questions.");
//    }
//
//    @Test
//    public void testCanTakeQuiz() {
//        // Initially, the student should be able to take the quiz
//        assertTrue(revisionQuiz.canTakeQuiz(student1), "Student1 should be able to take the quiz initially.");
//
//        // Track first attempt
//        revisionQuiz.trackQuizAttempt(student1);
//        assertTrue(revisionQuiz.canTakeQuiz(student1), "Student1 should still be able to take the quiz after 1 attempt.");
//
//        // Track second attempt
//        revisionQuiz.trackQuizAttempt(student1);
//        assertFalse(revisionQuiz.canTakeQuiz(student1), "Student1 should not be able to take the quiz after 2 attempts.");
//    }
//
//    @Test
//    public void testTrackQuizAttempt() {
//        // Ensure the student has 0 attempts initially
//        assertTrue(revisionQuiz.canTakeQuiz(student2), "Student2 should be able to take the quiz initially.");
//
//        // Track attempts
//        revisionQuiz.trackQuizAttempt(student2);
//        assertTrue(revisionQuiz.canTakeQuiz(student2), "Student2 should be able to take the quiz after 1 attempt.");
//
//        revisionQuiz.trackQuizAttempt(student2);
//        assertFalse(revisionQuiz.canTakeQuiz(student2), "Student2 should not be able to take the quiz after 2 attempts.");
//    }
//}
