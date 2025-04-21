package com.example.UnitTesting;

import com.example.question.FreeResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class FreeResponseTest {

    // Test for correct answer
    @Test
    public void testCorrectAnswer() {
        FreeResponse freeResponse = new FreeResponse("What is the capital of France?", "Paris");
        assertTrue("The answer should be correct.", freeResponse.checkAnswer("Paris"));
    }

    // Test for incorrect answer
    @Test
    public void testIncorrectAnswer() {
        FreeResponse freeResponse = new FreeResponse("What is the capital of France?", "Paris");
        assertFalse("The answer should be incorrect.", freeResponse.checkAnswer("London"));
    }

    // Test for case-insensitive correct answer
    @Test
    public void testCaseInsensitiveCorrectAnswer() {
        FreeResponse freeResponse = new FreeResponse("What is the capital of France?", "Paris");
        assertTrue("The answer should be correct regardless of case.", freeResponse.checkAnswer("pARis"));
    }

    // Test with extra spaces in the answer
    @Test
    public void testAnswerWithExtraSpaces() {
        FreeResponse freeResponse = new FreeResponse("What is the capital of France?", "Paris");
        assertTrue("The answer with extra spaces should be correct.", freeResponse.checkAnswer("  Paris  "));
    }
    // Test checkAnswer method in FreeResponse to ensure it trims the answer
    @Test
    public void testCheckAnswerTrimFreeResponse() {
        FreeResponse freeResponse = new FreeResponse("What is the capital of France?", "Paris");
        Assertions.assertTrue(freeResponse.checkAnswer("  Paris  "),
                "checkAnswer should correctly trim the answer in FreeResponse.");
    }
}
