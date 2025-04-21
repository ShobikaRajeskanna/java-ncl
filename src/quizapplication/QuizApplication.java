package com.example.quizapplication;



import com.example.question.FreeResponse;
import com.example.question.MCQ;
import com.example.question.Questions;

import java.util.*;
import java.util.stream.Collectors;

public class QuizApplication {
    public static void main(String[] args) {


        List<Questions> questions = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Quiz Application ");
        System.out.println("Enter your First Name");
        String firstName = scanner.nextLine();
        System.out.println("Enter your Last Name");
        String lastName = scanner.nextLine();
        Date birthDate = new Date();
        System.out.println("Enter your Date of Birth{Format:dd-mm-yyyy}");
        String birthofDate = (scanner.nextLine());

        Student student = new Student(firstName, lastName, birthDate);
        StudentStats statistics = student.getStats();
        ArrayList<Questions> questionBank = new ArrayList<>();
        Map<Student, Integer> attempts = new HashMap<>();
        Map<Student, Integer> revisionAttempts = new HashMap<>();


        while (true) {
            // Check if the student has already completed all quizzes
            if (statistics.hasFinalVerdict()) {
                System.out.println("You have completed the assessment with a final verdict: " + statistics.getFinalVerdict());
                System.out.println("Quiz History:\n" + statistics.getQuizHistory());
                break;
            }


            questionBank.add(new FreeResponse("Which element has the chemical symbol 'O'?", "Oxygen"));
            questionBank.add(new FreeResponse("Who wrote romeo and juliet?", "William Shakespeare"));
            questionBank.add(new FreeResponse("What is the largest planet in the solar system?", "Jupiter"));
            questionBank.add(new FreeResponse("Who is known as Father of Geometry?", "Euclid"));
            questionBank.add(new FreeResponse("Who discovered gravity?", "Newton"));


            questionBank.add(new MCQ("What is main ingredient of bread?", List.of("Sugar", "Flour", "salt", "rice"), List.of("1")));
            questionBank.add(new MCQ("what is a?", List.of("Sugar", "Flour", "salt", "rice"), List.of("2", "3","4")));
            questionBank.add(new MCQ("What is main ingredient of b?", List.of("Sugar", "Flour", "salt", "rice"), List.of("1", "2")));
            questionBank.add(new MCQ("What is main ingredient of c?", List.of("Sugar", "Flour", "salt", "rice"), List.of("3", "2")));
            questionBank.add(new MCQ("What is main ingredient of d?", List.of("Sugar", "Flour", "salt", "rice"), List.of("4", "1")));
            questionBank.add(new MCQ("What is main ingredient of r?", List.of("Sugar", "Flour", "salt", "rice"), List.of("2", "4")));

            System.out.println("\nHello, " + student.getFirstName() + "! Please choose an option:");
            System.out.println("1. Take Regular Quiz");
            System.out.println("2. Take Revision Quiz");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            int currentRevisionAttempts = revisionAttempts.getOrDefault(student, 0);
            int currentRegularAttempts = attempts.getOrDefault(student, 0);
            if (currentRevisionAttempts >= 2 && choice.equals("2") && currentRegularAttempts <=2) {
                System.out.println("You cannot take the Revision quiz again because you have used all attempts.");
                printStats(student);
                return;
            }
            if (currentRegularAttempts >= 2 && choice.equals("1") && currentRevisionAttempts <= 2) {
                System.out.println("You cannot take the Regular quiz again because you have used all attempts.");
                printStats(student);
                return;
            }


            switch (choice) {
                case "1":
                    handleRegularQuiz(scanner, student, statistics,questionBank, questions,attempts);
                    attempts.put(student, currentRegularAttempts + 1);
                    break;
                case "2":
                    handleRevisionQuiz(scanner, student, statistics,questionBank, questions,revisionAttempts );
                    revisionAttempts.put(student, currentRevisionAttempts + 1);

                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;

            }

            System.out.println("Number of regular attempts: " + attempts.get(student));

            System.out.println("Number of revision attempts: " + revisionAttempts.get(student));


        }


        scanner.close();


    }
    public static void handleRevisionQuiz(Scanner scanner, Student student, StudentStats statistics, ArrayList<Questions> questionBank,List<Questions> questions,Map<Student, Integer> revisionAttempts) {

        // Separate MCQs and Free Response questions
        List<MCQ> mcqQuestions = new ArrayList<>();
        List<FreeResponse> freeResponseQuestions = new ArrayList<>();

        // Populate the lists based on question types
        for (Questions question : questionBank) {
            if (question instanceof MCQ) {
                mcqQuestions.add((MCQ) question);
            } else if (question instanceof FreeResponse) {
                freeResponseQuestions.add((FreeResponse) question);
            }
        }

        // Shuffle the question lists
        Collections.shuffle(mcqQuestions);
        Collections.shuffle(freeResponseQuestions);

        // Select the first two MCQs and three Free Response questions
        List<Questions> selectedQuestions = new ArrayList<>();
        for (int i = 0; i < Math.min(2, mcqQuestions.size()); i++) {
            selectedQuestions.add(mcqQuestions.get(i));
        }
        for (int i = 0; i < Math.min(3, freeResponseQuestions.size()); i++) {
            selectedQuestions.add(freeResponseQuestions.get(i));
        }

        // Variables to track score
        int correctAnswers = 0;
        int totalQuestions = selectedQuestions.size();

        // Ask the selected questions
        for (Questions question : selectedQuestions) {
            System.out.println(question.getQuestionsText());

            if (question instanceof MCQ) {
                MCQ mcq = (MCQ) question;
                List<String> choices = mcq.getChoices();
                for (int j = 0; j < choices.size(); j++) {
                    System.out.println((j + 1) + ". " + choices.get(j));
                }
                System.out.println("Enter your answers (comma-separated): ");
                String userInput = scanner.nextLine();
                List<String> userAnswerStrings = Arrays.asList(userInput.split(","));
                userAnswerStrings = userAnswerStrings.stream().map(String::trim).collect(Collectors.toList()); // Trim whitespace

                boolean isCorrect = mcq.checkMCQAnswer(userAnswerStrings);
                if (isCorrect) {
                    System.out.println("Correct!");
                    correctAnswers++;
                } else {
                    System.out.println("Incorrect");
                }
            } else if (question instanceof FreeResponse) {
                String answer = scanner.nextLine();
                boolean isCorrect = question.checkAnswer(answer);
                if (isCorrect) {
                    System.out.println("Correct!");
                    correctAnswers++;
                } else {
                    System.out.println("Incorrect");
                }
            }



        }
        double percentage = ((double) correctAnswers / totalQuestions) * 100;
        double score=(double)correctAnswers/totalQuestions;
        System.out.println("Your Mark Percentage : "+ percentage + "%");
        boolean passed = percentage >= 50;
        if (passed) {
            System.out.println("Congratulations! You've passed the revision quiz.");
            System.out.println();
            student.setPassedQuiz(true); // Mark the student as having passed
        } else {
            System.out.println("You did not pass the quiz. Better luck next time!");
        }
        statistics.addRevisionAttempt(score);
    }


    public static void handleRegularQuiz(Scanner scanner, Student student, StudentStats statistics, ArrayList<Questions> questionBank, List<Questions> questions, Map<Student, Integer> attempts) {
        // Directly check if the student can take the quiz using the conditions
        if (student instanceof Student) {
            // Check if the student has already passed the quiz
            if (student.hasPassedQuiz()) {
                System.out.println("You cannot take the quiz again because you have already passed.");
                return;
            }

        } else {
            System.out.println("Invalid student instance.");
            return;
        }

        // Separate MCQs and Free Response questions
        List<MCQ> mcqQuestions = new ArrayList<>();
        List<FreeResponse> freeResponseQuestions = new ArrayList<>();

        // Populate the lists based on question types
        for (Questions question : questionBank) {
            if (question instanceof MCQ) {
                mcqQuestions.add((MCQ) question);
            } else if (question instanceof FreeResponse) {
                freeResponseQuestions.add((FreeResponse) question);
            }
        }

        // Shuffle the question lists
        Collections.shuffle(mcqQuestions);
        Collections.shuffle(freeResponseQuestions);

        // Select the first two MCQs and three Free Response questions
        List<Questions> selectedQuestions = new ArrayList<>();
        for (int i = 0; i < Math.min(2, mcqQuestions.size()); i++) {
            selectedQuestions.add(mcqQuestions.get(i));
        }
        for (int i = 0; i < Math.min(3, freeResponseQuestions.size()); i++) {
            selectedQuestions.add(freeResponseQuestions.get(i));
        }

        // Variables to track score
        int correctAnswers = 0;
        int totalQuestions = selectedQuestions.size();

        // Ask the selected questions
        for (Questions question : selectedQuestions) {
            System.out.println(question.getQuestionsText());

            if (question instanceof MCQ) {
                MCQ mcq = (MCQ) question;
                List<String> choices = mcq.getChoices();
                for (int j = 0; j < choices.size(); j++) {
                    System.out.println((j + 1) + ". " + choices.get(j));
                }
                System.out.println("Enter your answers (comma-separated): ");
                String userInput = scanner.nextLine();
                List<String> userAnswerStrings = Arrays.asList(userInput.split(","));
                userAnswerStrings = userAnswerStrings.stream().map(String::trim).collect(Collectors.toList()); // Trim whitespace

                boolean isCorrect = mcq.checkMCQAnswer(userAnswerStrings);
                if (isCorrect) {
                    System.out.println("Correct!");
                    correctAnswers++;
                } else {
                    System.out.println("Incorrect");
                }
            } else if (question instanceof FreeResponse) {
                String answer = scanner.nextLine();
                boolean isCorrect = question.checkAnswer(answer);
                if (isCorrect) {
                    System.out.println("Correct!");
                    correctAnswers++;
                } else {
                    System.out.println("Incorrect");
                }
            }
        }

        double percentage = ((double) correctAnswers / totalQuestions) * 100;
        double score=(double)correctAnswers/totalQuestions;
        System.out.println("Your Mark Percentage : "+ percentage + "%");
        boolean passed = percentage >= 50;
        if (passed) {
            System.out.println("Congratulations! You've passed the quiz.");
            System.out.println();
            student.setPassedQuiz(true); // Mark the student as having passed
        } else {
            System.out.println("You did not pass the quiz. Better luck next time!");
        }

        statistics.addQuizAttempt(score);

    }

    private static void printStats(Student student) {
        StudentStats stats = student.getStats();
        System.out.println("Student's final verdict: " + stats.getFinalVerdict());
        System.out.println("Number of attempts: " + stats.getAttempts());
        System.out.println("Number of revision attempts: " + stats.getRevisionAttempts());
    }



}