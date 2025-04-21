package com.example.UnitTesting;
import com.example.question.FreeResponse;
import com.example.question.MCQ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class MCQTest {

    // Test for correct list of answers using checkMCQAnswer method
    @Test
    public void testCorrectMCQAnswer() {
        List<String> choices = Arrays.asList("Option1", "Option2", "Option3", "Option4");
        List<String> correctAnswers = Arrays.asList("Option1", "Option3");
        MCQ mcq = new MCQ("Select the correct options", choices, correctAnswers);

        // Simulating user answer
        List<String> userAnswers = Arrays.asList("Option1", "Option3");
        assertTrue(mcq.checkMCQAnswer(userAnswers), "The MCQ answer should be correct.");
    }

    // Test for incorrect list of answers using checkMCQAnswer method
    @Test
    public void testIncorrectMCQAnswer() {
        List<String> choices = Arrays.asList("Option1", "Option2", "Option3", "Option4");
        List<String> correctAnswers = Arrays.asList("Option1", "Option3");
        MCQ mcq = new MCQ("Select the correct options", choices, correctAnswers);

        // Simulating incorrect user answer
        List<String> userAnswers = Arrays.asList("Option1", "Option2");
        assertFalse(mcq.checkMCQAnswer(userAnswers), "The MCQ answer should be incorrect.");
    }

    // Test with user answers provided in a different order
    @Test
    public void testCorrectMCQAnswerWithDifferentOrder() {
        List<String> choices = Arrays.asList("Option1", "Option2", "Option3", "Option4");
        List<String> correctAnswers = Arrays.asList("Option1", "Option3");
        MCQ mcq = new MCQ("Select the correct options", choices, correctAnswers);

        // Simulating user answer in different order
        List<String> userAnswers = Arrays.asList("Option3", "Option1");
        assertTrue(mcq.checkMCQAnswer(userAnswers), "The MCQ answer should be correct even if the order is different.");
    }

    // Test with partial correct answers
    @Test
    public void testPartialCorrectMCQAnswer() {
        List<String> choices = Arrays.asList("Option1", "Option2", "Option3", "Option4");
        List<String> correctAnswers = Arrays.asList("Option1", "Option3");
        MCQ mcq = new MCQ("Select the correct options", choices, correctAnswers);

        // Simulating partial user answer
        List<String> userAnswers = Arrays.asList("Option1");
        assertFalse(mcq.checkMCQAnswer(userAnswers), "The MCQ answer should be incorrect because it's incomplete.");
    }

    // Test for empty user answer
    @Test
    public void testEmptyUserAnswer() {
        List<String> choices = Arrays.asList("Option1", "Option2", "Option3", "Option4");
        List<String> correctAnswers = Arrays.asList("Option1", "Option3");
        MCQ mcq = new MCQ("Select the correct options", choices, correctAnswers);

        // Simulating empty user answer
        List<String> userAnswers = Arrays.asList();
        assertFalse(mcq.checkMCQAnswer(userAnswers), "The MCQ answer should be incorrect because no answer is given.");
    }

    // Test if the correct choices are retrieved
    @Test
    public void testGetChoices() {
        List<String> choices = Arrays.asList("Option1", "Option2", "Option3", "Option4");
        List<String> correctAnswers = Arrays.asList("Option1", "Option3");
        MCQ mcq = new MCQ("Select the correct options", choices, correctAnswers);

        assertEquals(choices, mcq.getChoices(), "The choices should match the original list.");
    }
    // Test if checkAnswer calls checkAnswerLogical in FreeResponse
    @Test
    public void testCheckAnswerCallsCheckAnswerLogicalFreeResponse() {
        FreeResponse freeResponse = new FreeResponse("What is the capital of France?", "Paris");
        assertTrue(freeResponse.checkAnswer("Paris"),
                "checkAnswer should correctly call checkAnswerLogical in FreeResponse.");
    }
}
