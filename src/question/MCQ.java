package com.example.question;

import java.util.*;

public class MCQ extends AbstractQuestions{

    private final List<String> choices;
    private List<String> correctAnswers;

    public MCQ(String QuestionText, List<String> choices, List<String> correctAnswers)
    //follow an algorithm for choosing the answer
    {
        super(QuestionText);
        this.choices = new ArrayList<>(choices);
        this.correctAnswers = new ArrayList<>(correctAnswers);
    }

    public List<String> getChoices() {
        return choices;
    }

    public boolean checkMCQAnswer(List<String> userAnswerStrings) {
        // Create sets for comparison
        HashSet<String> userAnswersSet = new HashSet<>(userAnswerStrings);
        HashSet<String> correctAnswersSet = new HashSet<>(correctAnswers);
        return userAnswersSet.equals(correctAnswersSet);
    }


    @Override
    protected boolean checkAnswerLogical(String answer) {
        Set<String> answerSet = new HashSet<>(Arrays.asList(answer.split(",")));
        return answerSet.equals(correctAnswers);
    }

}
