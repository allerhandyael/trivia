package com.example.quizquest;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

//import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStartGame, btnSetting, btnScoreBord;
    private String backgroundColor = "Blue";
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private com.example.quizquest.FbModule fbModule;
    private LinearLayout linearLayout;
    Cd_typeOfQuestions cdTypeOfQuestions;
    //private String Yes_or_no;
    //private String American;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();// קריאה לפונקציה שמאתחלת את כל הרכיבים
        cdTypeOfQuestions = new Cd_typeOfQuestions(this);
        
    }
   // public static <Gson> void main(String[] args) throws IOException {
        // Sample JSON string (you can also load it from a file)
      //  String json = "";

        // Initialize Gson
      //  Gson gson = new Gson();

        // Parse the JSON string into a QuestionsList object
     //   American_Questions questionsList = gson.fromJson(json, American_Questions.class);

        // Get the list of questions
     //   ArrayList<Question> questions = questionsList.getQuestions();
    //}

    private void init()
    {// לאתחול כפתורים, מאזינים והפיירבייס מודיל
        btnStartGame = findViewById(R.id.btnStartGame);
        btnStartGame.setOnClickListener(this);
        btnSetting = findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(this);
        btnScoreBord = findViewById(R.id.btnScorBord);
        btnScoreBord.setOnClickListener(this);


        linearLayout = findViewById(R.id.main);

        activityResultLauncher = registerForActivityResult(//יוצרים את הarl אשר ישמש אותנו בon clik //
                //למעבר לסטינג אקטיביטי
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {//משמשת לקבלת התשובה (color ) מה setting
                        //הפעולה מקבלת כפרמטר intent של המידע שהוספנו בputExtra
                        // אם התוצאה שהתקבלה היא תקינה (RESULT_OK)

                            if (result.getResultCode() == RESULT_OK) {
                                Intent data = result.getData();
                                String str = data.getStringExtra("color");
                                if (str != null) {
                                    setBackgroundColor(str); // Call a method to update
                                    fbModule.changeBackgroundColorInFireBase(str); // Save to Firebase
                                    Toast.makeText(MainActivity.this, "" + str, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }



                });

        fbModule = new FbModule(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnStartGame)
        {
            cdTypeOfQuestions.show();
            //Intent intent = new Intent(this, GameActivity.class);
            //intent.putExtra("color", backgroundColor);
            //intent.putExtra("value", 7);
            //startActivity(intent);//עוברים לגיים אקטיבי טי ונחזור לפה עם הפקודה פינייש
        }
        if(v == btnSetting)//אם לחצו על ההגדרות
        {
            Intent i = new Intent(this, SettingActivity.class);// יצירת אינטנט שאיתו נעבור לאקטיביטי האחר
            activityResultLauncher.launch(i);//מחכה לתשובה (הצבע)

        }
        if(v == btnScoreBord)
        {
            Intent intent = new Intent(MainActivity.this, ScoreBord.class);
            startActivity(intent);//עוברים ללוח תוצאותן

        }

    }

    public void setBackgroundColor(String str)
    { // עדכון צבע הרקע  בהתאם לצבע שנבחר
        backgroundColor = str;//שןמרת את הצבע
        switch (str)
        {
            case "Blue":
            {
                linearLayout.setBackgroundColor(Color.BLUE);
                break;
            }

            case "Red":
            {
                linearLayout.setBackgroundColor(Color.RED);
                break;
            }

            case "Pink":
            {
                linearLayout.setBackgroundColor(Color.argb(255,255,192,203));
                //linearLayout.setBackgroundColor(0xFFFFC0CB);
                break;
            }
            case "Yellow":
            {
                linearLayout.setBackgroundColor(Color.argb(150,255,255,0));
            }

            default:
                break;
        }
    }

    public void move(String ans) {
        if(ans.equals("NoOrYes"))
        {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("type","Yes_or_no");
            startActivity(intent);
            
        }
        if (ans.equals("Americans")) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("type", "American");
            startActivity(intent);
        }
       
       
    }
}