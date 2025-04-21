package com.example.question;

public abstract class AbstractQuestions implements Questions {

    private final String questionText;

    protected AbstractQuestions(String questionText) {
        this.questionText = questionText;
    }

    @Override
    public String getQuestionsText() {
        return questionText;
    }

    protected abstract boolean checkAnswerLogical(String answer);

    @Override
    public boolean checkAnswer(String answer) {
        return checkAnswerLogical(answer.trim());
    }
}

