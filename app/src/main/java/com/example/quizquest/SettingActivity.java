package com.example.quizquest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner,spinnertimes;
    private String[] arrcolor = { "", "Red", "Blue", "Pink", "Yellow"};
    private String[] arrtimes={"","30Sec","60 Sec","90 Sec"};
    private boolean isFirstTime = true;//Without the isFirstTime check, the activity
    // would immediately handle the selection of the first item in the spinner
    // ("" in this case) as if the user made a choice.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        spinner = findViewById(R.id.spinner);
        spinnertimes=findViewById(R.id.spinner2);

        spinner.setOnItemSelectedListener(this);
        spinnertimes.setOnItemSelectedListener(this);


        ArrayAdapter aa =
                new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrcolor);
        ArrayAdapter bb=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrtimes);
        //הadopterלוקח את המחרוזות מהמערך(arrColor) ושם אותם על הitenמים שב spinner
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertimes.setAdapter(bb);

}


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this, "onSelected", Toast.LENGTH_SHORT).show();
        if(!isFirstTime)
        {
            Intent intent = new Intent();//יוצרים אינטנט על מנת להעביר את מה שבחרו (עם put extra)
            intent.putExtra("color",arrcolor[position]);
            setResult(RESULT_OK, intent);//result_canceledתמד יהיה תקין,אם לא היינו רוצים להחזירערך היינו שמים ב
            finish();//חוזרים לmain
        }
        isFirstTime = false;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Nothing", Toast.LENGTH_SHORT).show();

    }
}