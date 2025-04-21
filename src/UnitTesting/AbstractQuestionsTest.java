//package com.example.UnitTesting;
//
//import com.example.question.FreeResponse;
//import com.example.question.MCQ;
////import org.junit.jupiter.api.Test;
////import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class AbstractQuestionsTest {
//
//    // Test if question text is retrieved correctly using FreeResponse
//    @Test
//    public void testGetQuestionTextFreeResponse() {
//        FreeResponse freeResponse = new FreeResponse("What is the capital of France?", "Paris");
//        assertEquals("What is the capital of France?", freeResponse.getQuestionsText(),
//                "The question text should be correctly retrieved from FreeResponse.");
//    }
//
//    // Test if question text is retrieved correctly using MCQ
//    @Test
//    public void testGetQuestionTextMCQ() {
//        List<String> choices = Arrays.asList("Option1", "Option2", "Option3");
//        List<String> correctAnswers = Arrays.asList("Option1", "Option3");
//        MCQ mcq = new MCQ("Select the correct options", choices, correctAnswers);
//
//        assertEquals("Select the correct options", mcq.getQuestionsText(),
//                "The question text should be correctly retrieved from MCQ.");
//    }
//
//
//}
