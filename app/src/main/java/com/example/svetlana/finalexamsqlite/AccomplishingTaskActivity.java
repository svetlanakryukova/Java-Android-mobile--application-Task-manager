package com.example.svetlana.finalexamsqlite;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AccomplishingTaskActivity extends AppCompatActivity {

    TextView title_input, priority_input;
    Button delete_button;
    String id, title, priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplishingtask);
        title_input = findViewById(R.id.title_input2);
        priority_input = findViewById(R.id.priority_input2);
        delete_button = findViewById(R.id.delete_button);
        getAndSetIntentData();
       ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title); // set title from main menu
        }
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("priority")){
            //Getting Data from Intent in RV
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            priority = getIntent().getStringExtra("priority");
            //Setting Intent Data
            title_input.setText(title);
            priority_input.setText(priority);
            Log.d("svetlana", title+" "+ priority);
        }else{
            Toast.makeText(this, "No data from this task", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure?");
        builder.setPositiveButton("Accomplished", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SQLiteDBHelper myDB = new SQLiteDBHelper(AccomplishingTaskActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
