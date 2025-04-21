package com.example.UnitTesting;
import com.example.question.FreeResponse;
import com.example.question.MCQ;
import com.example.question.Questions;
import com.example.quizapplication.QuizApplication;
import com.example.quizapplication.Student;
import com.example.quizapplication.StudentStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class QuizApplicationTest {

    private ByteArrayOutputStream outputStream;
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("".getBytes());
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testHandleRegularQuiz() {
        String initialinput = "John\nDoe\n01-01-2000\n1";
        System.setIn(new ByteArrayInputStream(initialinput.getBytes()));

        // Create a mock list of questions
        ArrayList<Questions> questionBank = new ArrayList<>();

        questionBank.add(new MCQ("What is 2 + 2?", List.of("3", "4", "5"), List.of("2")));

        // Create mock scanner input for the student response
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2000, Calendar.JUNE, 18); // November 18, 2000

        // Create a mock student and stats
        Student student = new Student("John", "Doe", calendar1.getTime());
        StudentStats stats = new StudentStats();
        student.getStats();
        ArrayList<Questions> questions = new ArrayList<>();

        // Execute the quiz
        QuizApplication.handleRegularQuiz(scanner, student, stats, questionBank, questions, new HashMap<>());

        // Verify the output and the stats
        String output = outputStream.toString();
        assertTrue(output.contains("Congratulations! You've passed the quiz."));
        assertEquals(1, stats.getAttempts());  // Assuming this tracks the number of quiz attempts
    }

    @Test
    void testHandleRevisionQuiz() {
        // Create a mock list of questions
        ArrayList<Questions> questionBank = new ArrayList<>();
        questionBank.add(new FreeResponse("What is the capital of France?", "Paris"));


        // Create mock scanner input for the student response
        String input = "Paris\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2000, Calendar.NOVEMBER, 18); // November 18, 2000

        // Create a mock student and stats
        Student student = new Student("John", "Doe",calendar1.getTime());
        StudentStats stats = new StudentStats();
        student.getStats();
        ArrayList<Questions> questions = new ArrayList<>();

        // Execute the revision quiz
        QuizApplication.handleRevisionQuiz(scanner, student, stats, questionBank, questions, new HashMap<>());

        // Verify the output and the stats
        String output = outputStream.toString();
        assertTrue(output.contains("Congratulations! You've passed the revision quiz."));
        assertEquals(1, stats.getRevisionAttempts());  // Assuming this tracks the number of revision quiz attempts
    }

    @Test
    void testQuizApplicationMain() {
        // Mock the input for a full session of the quiz
        String input = "John\nDoe\n01-01-2000\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute the main method
        QuizApplication.main(new String[]{});

        // Verify the output contains expected strings
        String output = outputStream.toString();

        assertTrue(output.contains("Congratulations! You've passed the quiz."));
    }

    @Test
    void testInvalidOptionInMenu() {
        // Mock the input to simulate an invalid option choice
        String input = "John\nDoe\n01-01-2000\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute the main method
        QuizApplication.main(new String[]{});

        // Verify the output contains the error message
        String output = outputStream.toString();
        assertTrue(output.contains("Invalid choice. Try again."));
    }

    @Test
    void testExceededAttempts() {
        // Simulate exceeding the maximum number of attempts for the regular quiz
        String input = "John\nDoe\n01-01-2000\n1\nParis\n2\n1\nParis\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute the main method
        QuizApplication.main(new String[]{});

        // Verify that the user is informed they can't take the quiz again
        String output = outputStream.toString();
        assertTrue(output.contains("You cannot take the Regular quiz again because you have used all attempts."));
    }

    @Test
    void testRevisionExceededAttempts() {
        // Simulate exceeding the maximum number of revision quiz attempts
        String input = "John\nDoe\n01-01-2000\n2\nParis\n2\n2\nParis\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute the main method
        QuizApplication.main(new String[]{});

        // Verify that the user is informed they can't take the revision quiz again
        String output = outputStream.toString();
        assertTrue(output.contains("You cannot take the Revision quiz again because you have used all attempts."));
    }
}
