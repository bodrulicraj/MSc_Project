package com.iamdj.countrycomparison;

import static com.iamdj.countrycomparison.DemoClass.executionTime;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    Button btnInsertData;
    EditText textId, textName, textContinent, textCities, textPark, textAirports, textHotels;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        myDB = new DatabaseHelper(this);  // created object of DatabaseHelper class

        //myDB.getWritableDatabase(); // for checking db is created or not.

        btnInsertData = findViewById(R.id.btnInsertData);
        textId = findViewById(R.id.textId);
        textName = findViewById(R.id.textName);
        textContinent = findViewById(R.id.textContinent);
        textCities = findViewById(R.id.textCities);
        textPark = findViewById(R.id.textPark);
        textAirports = findViewById(R.id.textAirports);
        textHotels = findViewById(R.id.textHotels);
        textView1= findViewById(R.id.executionTimeview);

        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // Insert data into database
                boolean isInserted = myDB.insertData(textName.getText().toString(), textContinent.getText().toString(), textCities.getText().toString(),textPark.getText().toString(),textAirports.getText().toString(),textHotels.getText().toString());


                //myDB.insertData(getText(startTime))

                // Show toast when data inserted successfully
                if(isInserted){
                    Toast.makeText(InsertActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InsertActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }

                // After inserting make edit box empty
                textId.setText("");
                textName.setText("");
                textContinent.setText("");
                textCities.setText("");
                textPark.setText("");
                textAirports.setText("");
                textHotels.setText("");
                textView1.setText("Execution Time: " +DemoClass.executionTime+" ms");

                Log.d("timelymain", String.valueOf(executionTime));
            }
        });



    }
    private void showMessage(String title, String Message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}