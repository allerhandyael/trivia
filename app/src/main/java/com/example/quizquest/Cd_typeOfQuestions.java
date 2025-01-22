package com.example.quizquest;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;

public class Cd_typeOfQuestions extends Dialog implements View.OnClickListener {
    private Button btnAmericans, btnYesOrNo;
    private Context context;

    public Cd_typeOfQuestions(@NonNull Context context) {
        super(context);
        setContentView(R.layout.cd_typeofquestions);  // שם הקובץ XML ללא כוכביות
        this.context = context;

        // מציאת הכפתורים ללא כוכביות
        btnAmericans = findViewById(R.id.btnAmericans);
        btnYesOrNo = findViewById(R.id.btnYesOrNo);

        // הגדרת מאזינים
        btnYesOrNo.setOnClickListener(this);
        btnAmericans.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (btnAmericans == view) {
            ((MainActivity)context).move("Americans");
            dismiss();
        }
        if (btnYesOrNo == view) {
            ((MainActivity)context).move("NoOrYes");
            dismiss();
        }
    }
}







