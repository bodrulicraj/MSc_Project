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

public class UpdateActivity extends AppCompatActivity {
    Button btnUpdateData;
    EditText textId, textName, textContinent, textCities,textPark,textAirports,textHotels;
    TextView textView1;


    DatabaseHelper myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        myDB = new DatabaseHelper(this);  // created object of DatabaseHelper class

        btnUpdateData = findViewById(R.id.btnUpdateData);

        textId = findViewById(R.id.textId);
        textName = findViewById(R.id.textName);
        textContinent = findViewById(R.id.textContinent);
        textCities = findViewById(R.id.textCities);
        textPark = findViewById(R.id.textPark);
        textAirports = findViewById(R.id.textAirports);
        textHotels = findViewById(R.id.textHotels);
        textView1= findViewById(R.id.executionTimeview);
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check emptiness of edit boxes
                if(textId.getText().toString().isEmpty() || textName.getText().toString().isEmpty() || textContinent.getText().toString().isEmpty() || textCities.getText().toString().isEmpty()|| textPark.getText().toString().isEmpty()|| textAirports.getText().toString().isEmpty() || textHotels.getText().toString().isEmpty()){
                    showMessage("Error","Please fill the all fields to Updating");
                    return;
                }
                boolean isUpdated = myDB.updateData(textId.getText().toString(), textName.getText().toString(), textContinent.getText().toString(),
                        textCities.getText().toString(),textPark.getText().toString(),textAirports.getText().toString(),textHotels.getText().toString());

                if(isUpdated){
                    Toast.makeText(UpdateActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpdateActivity.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                }

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