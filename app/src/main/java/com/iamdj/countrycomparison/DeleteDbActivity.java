package com.iamdj.countrycomparison;

import static com.iamdj.countrycomparison.DemoClass.executionTime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteDbActivity extends AppCompatActivity {

    Button btnDeleteData;
    EditText textId,textName;
    TextView textView1;


    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        myDB = new DatabaseHelper(this);  // created object of DatabaseHelper class



        textId = findViewById(R.id.textId);
        textName=findViewById(R.id.textName);

        textView1= findViewById(R.id.executionTimeview);
        btnDeleteData = findViewById(R.id.btnDeleteData);

        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer isDeleted  = myDB.deleteData(textId.getText().toString());

                if(isDeleted > 0){
                    Toast.makeText(DeleteDbActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DeleteDbActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                }

                textView1.setText("Execution Time: " +DemoClass.executionTime+" ms");


                Log.d("timedelmain", String.valueOf(executionTime));

            }

        });

    }
    }
