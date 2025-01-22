package com.example.quizquest;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class Cd_typeOfQuestions extends Dialog implements View.OnClickListener {//מחלקה היורשת את הדיאלוג של האנדרואיד
    Button btnAmericans, btnYesOrNo;
    Context context;
    Intent intent;
    String American_Questions,Yes_or_no;

    public Cd_typeOfQuestions(@NonNull Context context) {
        super(context);//מקבלים הפנייה לgame activity כפרמטר וקוראים לה context
        setContentView(R.layout.cd_typeofquestions);//שם שונה מה this של ה game activity
        this.context = context;// בלעדייו הקונטקסט לא נמצא בלמעלה (משתנה לוקאלי)

        btnAmericans = findViewById(R.id.btnAmericans);
        btnYesOrNo = findViewById(R.id.btnYesOrNo);
        btnYesOrNo.setOnClickListener(this);
        btnAmericans.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (btnAmericans == view) {
            ((MainActivity)context).move("Americans");
            dismiss();


/*          Intent intent;
            intent = new Intent(this, GameActivity.class);
            intent.putExtra("American_Questions",American_Questions);
            context.startActivity(intent);*/
        }

        if (btnYesOrNo == view) {
            ((MainActivity)context).move("NoOrYes");
            dismiss();


            //intent = new Intent(this, GameActivity.class);
            //intent.putExtra("YesOrNOquestions", Yes_or_no);
            //context.startActivity(intent);
        }



    }

    }



