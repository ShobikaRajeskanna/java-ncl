package com.example.question;

public class FreeResponse extends AbstractQuestions{

    //private String questionText;
    private String correctanswer;

        public FreeResponse(String questionText, String correctanswer)
        {
            super(questionText);
            this.correctanswer = correctanswer;
    }
    @Override
    public final boolean checkAnswer(String answer) {
        return checkAnswerLogical(answer.trim());
    }

    protected boolean checkAnswerLogical(String answer) {
        return answer.equalsIgnoreCase(correctanswer);

    }
}
