package com.example.quizquest;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FbModule {

    private MainActivity mainActivity;
    public FbModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("color");

        DatabaseReference ref = firebaseDatabase.getReference("Question");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {//הפרמטר סנאפשוט הינו הפנייה לצומת שהשתנה בה הערך
                String str = snapshot.getValue(String.class);
                if(str != null)
                {
                    mainActivity.setBackgroundColor(str);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void changeBackgroundColorInFireBase(String str){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("color");//שמים הפנייה לצומת ששמה colorורושמים אליו את המחרוזת
        reference.setValue(str);
    }

    public boolean howWasRight(DataSnapshot Snapshot)
    {
        char ansrr1 ;
        char anser2;
        return true;

    }

}
