package com.iamdj.countrycomparison;

import static com.iamdj.countrycomparison.DemoClass.executionTime;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReadActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    Button  btnReadData;
    EditText textId, textName, textContinent, textCities;
    TextView textView1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        myDB = new DatabaseHelper(this);  // created object of DatabaseHelper class

        //myDB.getWritableDatabase(); // for checking db is created or not.

        btnReadData = findViewById(R.id.btnReadData);


        textId = findViewById(R.id.textId);
        textName = findViewById(R.id.textName);
        textContinent = findViewById(R.id.textContinent);
        textCities = findViewById(R.id.textCities);

        textView1= findViewById(R.id.executionTimeview);

        btnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = myDB.getAllData();



                if(cur.getCount() == 0){
                    showMessage("Error","No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (cur.moveToNext()){

                    buffer.append("ID: "+ cur.getString(0)+"\n");  // 0 is index here shows columns in database
                    buffer.append("Name: "+ cur.getString(1)+"\n");
                    buffer.append("Continent: "+ cur.getString(2)+"\n");
                    buffer.append("Cities: "+ cur.getString(3)+"\n");
                    buffer.append("Parks: "+ cur.getString(4)+"\n");
                    buffer.append("Airports: "+ cur.getString(5)+"\n");
                    buffer.append("Hotels: "+ cur.getString(6)+"\n\n");

                }


                showMessage("Data",buffer.toString());

                textView1.setText("Execution Time: " +DemoClass.executionTime+" ms");
                Log.d("timereadmain", String.valueOf(executionTime));
            }

        });

    }
    // Method to show database table
    private void showMessage(String title, String Message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}