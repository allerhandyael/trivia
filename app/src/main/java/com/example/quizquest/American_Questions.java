package com.example.quizquest;

import android.content.Context;

import java.util.Random;

public class American_Questions {



    public int index; // Index for the next question
    private Question[] Americans_questions;

    private Random rnd;


    public American_Questions() {

        // Initialize questions
        Americans_questions = new Question[]{
                new Question("whats the longest river in the world?", new String[]{"Amazon", "the quite river", "easten","Nile"}, "Nile", "american"),
                new Question("who was the first president in the us?", new String[]{"Thomas Jeffeson","George Washington", "bakar obama", "israel ahavromovich"}, "George Washington", "american"),
                new Question("What planet is knowm as the red planet?", new String[]{"Venus", "earth","Saturn", "sun"}, "Saturn", "american"),
                new Question("who wrote the novel Pride and Justice?", new String[]{"Jane Austen", "amy whinhous","Charlotte Bronte", "bibi netaniaho"}, "Charlotte Bronte", "american"),
                new Question("which countryhas won the most FIFA World Cup titels?", new String[]{"italy","Brazil", "france", "israel"}, "italy", "american")
        };

        rnd = new Random();
    }

    public void initQuestions() {
        index = 0; // Reset the index

        // Shuffle the questions
        for (int i = Americans_questions.length - 1; i >= 0; i--) {
            int j = rnd.nextInt(i + 1); // Pick a random index
            // Swap questions[i] with questions[j]
            Question temp = Americans_questions[i];
            Americans_questions[i] = Americans_questions[j];
            Americans_questions[j] = temp;
        }
    }

    /**
     * Returns the next question in the sequence and increments the index.
     * If there are no more questions, it returns null.
     *
     * @return the next Question object or null if all questions are used
     */

    public Question getNextQuestion(Context context) {
        if (index >= Americans_questions.length) {
            index++;
            //return null; // אין יותר שאלות
        }


        // מחזיר את השאלה הבאה ומעלה את האינדקס
        Question q = Americans_questions[index];
        index++;
        return q;

    }

    public boolean isNotLastQuestion() {
        // Check if we are at the last question
        return (index < Americans_questions.length); // Compare with array length
    }

    public int getIndex() {
        return index;
    }

    public int getQnumber() {
        return Americans_questions.length;
    }

//    public Question getNextQuestion() {
//    }

}

