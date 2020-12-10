package com.example.svetlana.finalexamsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.widget.Toast.*;

public class TaskActivity extends AppCompatActivity {

    EditText title_input;
    TextView priority_input;
    SeekBar sb;
    Button save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        sb=findViewById(R.id.seekBar);

        priority_input = findViewById(R.id.priority_input);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStopTrackingTouch(SeekBar sb){

            }
            @Override
            public void onStartTrackingTouch(SeekBar sb){

            }
            @Override
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser){

                priority_input.setText(String.valueOf(progress+1));
            }

        });

        title_input = findViewById(R.id.title_input);

        save_button = findViewById(R.id.save_button);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityIntent = new Intent(getApplicationContext(), MainActivity.class);

                if (title_input.getText().toString().matches("")){
                    makeText(TaskActivity.this, "Task title cannot by empty", LENGTH_SHORT).show();
                    return;
                }
                else if(priority_input.getText().toString().matches("")){
                    makeText(TaskActivity.this, "Please send priority for task", LENGTH_SHORT).show();
                    return;
                }else{

                    SQLiteDBHelper myDB = new SQLiteDBHelper(TaskActivity.this);
                    myDB.saveTask(title_input.getText().toString().trim(),
                            priority_input.getText().toString().trim());
                            startActivity(activityIntent);
                }

            }
        });

    }
}
