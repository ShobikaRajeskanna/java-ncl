package com.example.quizapplication;

import java.util.*;

public class StudentStats {
    private String finalVerdict;
    private int attempts;
    private int revisionAttempts;
    private List<Double> quizScores;
    private List<Double> revisionScores;

    public StudentStats() {
        this.finalVerdict = "TBD";
        this.attempts = 0;
        this.revisionAttempts = 0;
        this.quizScores = new ArrayList<>();
        this.revisionScores = new ArrayList<>();
    }

    public void addQuizAttempt(double score) {
        attempts++;
        quizScores.add(score);

        if (score >= 0.5 && finalVerdict.equals("TBD")) {
            finalVerdict = "PASS";
        } else if (attempts == 2 && revisionAttempts == 2 && finalVerdict.equals("TBD")) {
            finalVerdict = "FAIL";
        }
    }

    public void addRevisionAttempt(double score) {
        revisionAttempts++;
        revisionScores.add(score);
    }

    public boolean hasFinalVerdict() {
        return !finalVerdict.equals("TBD");
    }

    public String getFinalVerdict() {
        return finalVerdict;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getRevisionAttempts() {
        return revisionAttempts;
    }

    public String getQuizHistory() {
        StringBuilder history = new StringBuilder();
        history.append("Quiz Scores: ").append(quizScores).append("\n");
        history.append("Revision Scores: ").append(revisionScores).append("\n");
        history.append("Attempts: ").append(attempts).append("\n");
        history.append("Revision Attempts: ").append(revisionAttempts).append("\n");
        return history.toString();
    }
}