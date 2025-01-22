package com.example.quizquest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btna1, btna2, btna3, btna4;
    private TextView tvQuestion;
    private TextView tvQuestionNumber, tvPoints, tvGameOver;
    private LinearLayout linearLayout;
    protected Handler handler;
    protected Runnable runnable;
    private TextView timerTextView;
    private String typeOfQuestions;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 30000;

    private Question currentQuestion;   // Pointer to the current question
    private American_Questions collectionAmericans;     // Collection of questions
    private Yes_or_no_questions yes_or_no_questions;

    private int points;
    private android.content.Context Context;
    //"מונחה אירועים" המשחקמתקדם כל פעם שיש אירוע(לחיצה)
    //האקטיביטי משתמש באותם רכיבים שהגדרנו בxml כאשר כל פעם לוקחיםמידע מהarraylist ומעצבים את הכתוב בתוך הרכיבים

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Intent i = getIntent();
        Intent i = getIntent();
        typeOfQuestions= i.getStringExtra("type");
        String backgroundColor = i.getStringExtra("color");
        if(backgroundColor != null)
        {
            setBackgroundColor(backgroundColor);
        }
        else
        {
            backgroundColor = "Blue";
        }


        linearLayout = findViewById(R.id.activity_game);


        tvQuestion = findViewById(R.id.tvQuestion);
        btna1 = findViewById(R.id.btna1);
        btna2 = findViewById(R.id.btna2);
        btna3 = findViewById(R.id.btna3);
        btna4 = findViewById(R.id.btna4);

        btna1.setOnClickListener(this);
        btna2.setOnClickListener(this);
        btna3.setOnClickListener(this);
        btna4.setOnClickListener(this);


        tvPoints = findViewById(R.id.tvPoints);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvGameOver = findViewById(R.id.tvGameOver);
        timerTextView = findViewById(R.id.timerTextView);

        tvGameOver.setVisibility(View.INVISIBLE);

        collectionAmericans = new American_Questions();
        collectionAmericans.initQuestions();  // Initialize the collection
        yes_or_no_questions=new Yes_or_no_questions();
        yes_or_no_questions.initQuestions();// Initialize the collection


        points = 0;  // Initialize points





    }

    private void setBackgroundColor(String backgroundColor) {
        switch (backgroundColor) {
            case "Blue":
                linearLayout.setBackgroundColor(Color.BLUE);
                break;
            case "Red":
                linearLayout.setBackgroundColor(Color.RED);
                break;
            case "Yellow":
                linearLayout.setBackgroundColor(Color.YELLOW);
                break;

            default:
                break;
        }
    }

    private void nextQuestion() {
        // הפעולה בודקת אם הגענו לשאלה האחרונה(אם כן יוצרת את הדיאלוג) ואם לא מראה את השאלה הבאה
        if (typeOfQuestions.equals("American")) {
            if (collectionAmericans.isNotLastQuestion()) {
                currentQuestion = collectionAmericans.getNextQuestion(Context); // Reference to the current question

                tvQuestion.setText(currentQuestion.getQuestion());

                btna1.setText(currentQuestion.getA1());
                btna2.setText(currentQuestion.getA2());
                btna3.setText(currentQuestion.getA3());
                btna4.setText(currentQuestion.getA4());

                // Update question number
                tvQuestionNumber.setText("Question number: " + (collectionAmericans.getIndex()) + "/" + (collectionAmericans.getQnumber()));

                // Start or reset the countdown timer when moving to the next question
                startCountdown();
            } else {
                // End of the collection, end of the game
                tvGameOver.setVisibility(View.VISIBLE);
                createDialog();  // Show the dialog asking if the user wants to play again
            }
        } else if (yes_or_no_questions.isNotLastQuestion()) {
            currentQuestion = yes_or_no_questions.getNextQuestion(Context); // Reference to the current question

            tvQuestion.setText(currentQuestion.getQuestion());

            btna1.setText(currentQuestion.getA1());
            btna2.setText(currentQuestion.getA2());
            btna3.setVisibility(View.INVISIBLE);
            btna4.setVisibility(View.INVISIBLE);

            // Update question number
            tvQuestionNumber.setText("Question number: " + (yes_or_no_questions.getIndex()) + "/" + (yes_or_no_questions.getQnumber()));

            // Start or reset the countdown timer when moving to the next question
            startCountdown();
        } else {
            // End of the collection, end of the game
            tvGameOver.setVisibility(View.VISIBLE);
            createDialog();  // Show the dialog asking if the user wants to play again
        }
    }


    private void createDialog() {
        CustomDialog customDialog = new CustomDialog(this); // יוצרים עצם מסוג דיאלוג //
        // ומעבירים בפעולה הבונה כפרמטר את הthis של game activity(הפנייה לגייםאקטיביטי)
        //הדבר נעשה על מנת שיהיה אפשר לגשת מתוך הדיאלוג לרייסט שנמצא פה(הדיאלוג סוג של מוכל בגיים אקטיביטי)
        customDialog.setOnDismissListener(dialog -> {
            // Handle what happens when dialog is dismissed
        });
        customDialog.show();  // Show the dialog

    }

    @Override
    public void onClick(View view) {
        if (currentQuestion != null) {
            int correctAnswer = currentQuestion.getCorrect();
            if (view == btna1 && correctAnswer == 1) points++;
            else if (view == btna2 && correctAnswer == 2) points++;
            else if (view == btna3 && correctAnswer == 3) points++;
            else if (view == btna4 && correctAnswer == 4) points++;

            // Update points display
            tvPoints.setText("Points: " + points);
        }

        // Move to the next question if available

        nextQuestion();

    }


    // The reset method to restart the game
    public void reset() {
        points = 0;  // Reset points
        collectionAmericans.initQuestions();
        yes_or_no_questions.initQuestions();
        // Re-initialize the questions
        tvPoints.setText("Points: " + points);  // Reset points view
        tvQuestionNumber.setText("Question number: 1");  // Reset question number view
        tvGameOver.setVisibility(View.INVISIBLE);  // Hide the game over message

        nextQuestion();  // Start the game with the first question
    }

    public void onTick(long millisUntilFinished) {
        // עדכון ה-TextView עם הזמן הנותר
        int secondsRemaining = (int) (millisUntilFinished / 1000);
        timerTextView.setText(String.valueOf(secondsRemaining));
    }

    private void startCountdown() {
        // זמן התחלה (במילישניות) - 30 שניות
        long startTime = 30000;

        // אם הטיימר רץ, נעצור אותו קודם
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        // הגדרת הטיימר עם זמן סיום וזמן עדכון
        countDownTimer = new CountDownTimer(startTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // עדכון ה-TextView עם הזמן הנותר
                int secondsRemaining = (int) (millisUntilFinished / 1000);
                timerTextView.setText(String.valueOf(secondsRemaining));
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Time's up!");
                nextQuestion(); // מתקדם לשאלה הבאה לאחר סיום הטיימר
            }
        };
        countDownTimer.start();  // התחלת הטיימר
    }

}