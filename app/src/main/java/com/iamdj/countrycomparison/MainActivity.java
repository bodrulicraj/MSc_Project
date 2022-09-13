package com.iamdj.countrycomparison;


import static com.iamdj.countrycomparison.DemoClass.executionTime;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //DemoClass demoClass=(DemoClass) this.getApplication();

    Button btnInsertData, btnCreate, btnDelete, btnUpdate, btnRead;
    EditText textId, textName, textContinent, textCities;
    TextView textView1;


    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        myDB = new DatabaseHelper(this);  // created object of DatabaseHelper class

        //myDB.getWritableDatabase(); // for checking db is created or not.


        btnInsertData = findViewById(R.id.btnInsertData);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnRead = findViewById(R.id.btnRead);
        btnCreate=findViewById(R.id.btnCreate);


        textId = findViewById(R.id.textId);
        textName = findViewById(R.id.textName);
        textContinent = findViewById(R.id.textContinent);
        textCities = findViewById(R.id.textCities);
        textView1= findViewById(R.id.executionTimeview);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, InsertActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, UpdateActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent myIntent = new Intent(MainActivity.this, ReadActivity.class);
                //MainActivity.this.startActivity(myIntent);
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

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, DeleteDbActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });




        /*btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // Insert data into database
                boolean isInserted = myDB.insertData(textName.getText().toString(), textContinent.getText().toString(), textCities.getText().toString());


                //myDB.insertData(getText(startTime))

                // Show toast when data inserted successfully
                if(isInserted){
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }

                // After inserting make edit box empty
                textId.setText("");
                textName.setText("");
                textContinent.setText("");
                textCities.setText("");
                textView1.setText("Execution Time: " +DemoClass.executionTime+" ms");

                Log.d("timelymain", String.valueOf(executionTime));
            }
        });*/

// btnReadData.setOnClickListener(new View.OnClickListener() {
//     @Override
//     public void onClick(View view) {
//         Cursor cur = myDB.getAllData();
//
//
//
//         if(cur.getCount() == 0){
//             showMessage("Error","No Data Found");
//             return;
//         }
//
//         StringBuffer buffer = new StringBuffer();
//         while (cur.moveToNext()){
//
//             buffer.append("ID: "+ cur.getString(0)+"\n");  // 0 is index here shows columns in database
//             buffer.append("Name: "+ cur.getString(1)+"\n");
//             buffer.append("Continent: "+ cur.getString(2)+"\n");
//             buffer.append("Cities: "+ cur.getString(3)+"\n\n");
//         }
//
//
//         showMessage("Data",buffer.toString());
//
//         textView1.setText("Execution Time: " +DemoClass.executionTime+" ms");
//         Log.d("timereadmain", String.valueOf(executionTime));
//     }
//
// });


        /*btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check emptiness of edit boxes
                if(textId.getText().toString().isEmpty() || textName.getText().toString().isEmpty() || textContinent.getText().toString().isEmpty() || textCities.getText().toString().isEmpty()){
                    showMessage("Error","Please fill the all fields to Updating");
                    return;
                }
                boolean isUpdated = myDB.updateData(textId.getText().toString(), textName.getText().toString(), textContinent.getText().toString(), textCities.getText().toString());

                if(isUpdated){
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                }

                textView1.setText("Execution Time: " +DemoClass.executionTime+" ms");
                Log.d("timelymain", String.valueOf(executionTime));
            }


        });
*/


        /*btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer isDeleted  = myDB.deleteData(textId.getText().toString());

                if(isDeleted > 0){
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                }

                textView1.setText("Execution Time: " +DemoClass.executionTime+" ms");


                Log.d("timedelmain", String.valueOf(executionTime));

            }

        });*/

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