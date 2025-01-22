package com.example.quizquest;

import android.content.Context;

import java.util.Random;

public class Yes_or_no_questions {

    int index = 0;
    private Random rnd;
    private Question[] Yes_or_no_Questions;
    ;//מערך אחד עם עצמים מסוג שאלה

    public Yes_or_no_questions() {
        Yes_or_no_Questions = new Question[]{
                new Question("the capital of France Paris", new String[]{"True", "False"}, "true", "NoOrYes"),
                new Question("Is the Earth round?", new String[]{"True", "False"}, "false", "NoOrYes"),
                new Question("Does water boil at 100°C?", new String[]{"True", "False"}, "true", "NoOrYes"),
                new Question("Was General Mohamed Shawarri the president of Egypt?", new String[]{"True", "False"}, "true", "NoOrYes"),
                new Question("which countryhas won the most FIFA World Cup titels?", new String[]{"True", "False"}, "false", "NoOrYes")
        };
        rnd = new Random();
    }


    public void initQuestions() {
        index = 0; // Reset the index

        // Shuffle the questions
        for (int i = Yes_or_no_Questions.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1); // Pick a random index
            // Swap questions[i] with questions[j]
            Question temp = Yes_or_no_Questions[i];
            Yes_or_no_Questions[i] = Yes_or_no_Questions[j];
            Yes_or_no_Questions[j] = temp;
        }
    }

    public boolean isNotLastQuestion() {
        return (index < Yes_or_no_Questions.length);
    }

    public int getIndex() {
        return index;
    }

    public int getQnumber() {
        return Yes_or_no_Questions.length;
    }

    public Question getNextQuestion(Context context) {
        if (index >= Yes_or_no_Questions.length) {
            index++;
            //return null; // אין יותר שאלות
        }


        // מחזיר את השאלה הבאה ומעלה את האינדקס
        Question q = Yes_or_no_Questions[index];
        index++;
        return q;

    }

}

