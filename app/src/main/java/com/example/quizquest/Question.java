package com.example.quizquest;

public class Question {

    private String question;


    private String[] answers;


    private String correctAnswer;


    private String type;

    public Question(String question, String[] answers, String correctAnswer, String type) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.type = type;
    }


    // Getters and Setters
    public String getQuestion() {
        return question;
    }

    public String getA1() {
        return this.answers[0];
    }

    public String getA2() {
        return this.answers[1];
    }
    public String getA3() {
        return this.answers[2];
    }
    public String getA4() {
        return this.answers[3];
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCorrect() {
        int i;
        for (i = 0; i < answers.length; i++) {
            if (answers[i] == correctAnswer)
                return i;
        }
        return i;

    }

}




