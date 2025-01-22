package com.example.quizquest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class sining_up extends AppCompatActivity {
    private Button register;
    private int username;
    private int paswwad;
    boolean isExist=true;
    int index;
    String str="passward not maching user name/has been used already";
    private sining_up [] Profiles;

    public sining_up(String username, int paswwad) {

        this.paswwad=R.id.passwordField;
        this.username=R.id.usernameText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sining_up);

    }

    public sining_up(Button register) {
        this.register = register;


        register.setOnClickListener((View.OnClickListener) this);
    }
    public void onClick(View view) {//לבדוק שכאשר נרשמים אין משתמש כזה כבר,לעדכן אם אין
        if (view==register) {
            for (int i = 0; i < Profiles.length; i++) {
                if (Profiles[i].paswwad == this.paswwad && Profiles[i].username != this.username) {
                    isExist = false;//כבר קיים משתמש כזה
                }


            }
            isExist = true;
            if (!isExist)
            {
                //Profiles[index]=new sining_up(this.username,this.paswwad);
            }

        }
    }



}