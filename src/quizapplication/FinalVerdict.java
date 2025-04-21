package com.example.quizapplication;

public enum FinalVerdict {

    PASS,
    FAIL,
    TBD;

    @Override
    public String toString() {
        switch (this) {
            case PASS:
                return "Pass";
            case FAIL:
                return "Fail";
            default:
                return "To Be Determined";
        }
    }
}
