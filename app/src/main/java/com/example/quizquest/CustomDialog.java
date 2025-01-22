package com.example.quizquest;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog implements View.OnClickListener {//מחלקה היורשת את הדיאלוג של האנדרואיד
    Button btnYes, btnNo;
    Context context;

    public CustomDialog(@NonNull Context context) {
        super(context);//מקבלים הפנייה לgame activity כפרמטר וקוראים לה context
        setContentView(R.layout.custom_dialog);//שם שונה מה this של ה game activity
        this.context = context;// בלעדייו הקונטקסט לא נמצא בלמעלה (משתנה לוקאלי)

        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (btnYes == view) {
            dismiss(); // Eliminate the dialog
            ((GameActivity) context).reset(); // Reset the game//נשארים באותו אקטיביטי//בעזרת הקונטקסט קוראים לרייסט שבגיים אקטיביטי
        }

        if (btnNo == view) {
            dismiss(); // Eliminate the dialog
            ((GameActivity) context).finish(); // Close the activity//מחזיר את המשתמש לmain
        }



    }

    }



