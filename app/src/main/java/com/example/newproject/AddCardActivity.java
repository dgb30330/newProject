package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ImageFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        String preQ = getIntent().getExtras().getString("preQ");
        String preA = getIntent().getExtras().getString("preA");
        String w1 = getIntent().getExtras().getString("wrong1");
        String w2 = getIntent().getExtras().getString("wrong2");
        String w3 = getIntent().getExtras().getString("wrong3");
        boolean editStatus = getIntent().getExtras().getBoolean("editStatus");

        if(!(preQ.equals("none"))){
            EditText qEntry = findViewById(R.id.new_question);
            qEntry.setText(preQ);
        }
        if(!(preA.equals("none"))){
            EditText aEntry = findViewById(R.id.new_answer);
            aEntry.setText(preA);
        }
        if(!(w1.equals("none"))){
            EditText nw1 = findViewById(R.id.new_wrong1);
            nw1.setText(w1);
        }
        if(!(w2.equals("none"))){
            EditText nw2 = findViewById(R.id.new_wrong2);
            nw2.setText(w2);
        }
        if(!(w3.equals("none"))){
            EditText nw3 = findViewById(R.id.new_wrong3);
            nw3.setText(w3);
        }

        ImageView cancelIcon = findViewById(R.id.cancel_icon);
        ImageView saveIcon = findViewById(R.id.save);


        cancelIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        saveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newQ = ((EditText) findViewById(R.id.new_question)).getText().toString();
                String newA = ((EditText) findViewById(R.id.new_answer)).getText().toString();
                String newW1 = ((EditText) findViewById(R.id.new_wrong1)).getText().toString();
                String newW2 = ((EditText) findViewById(R.id.new_wrong2)).getText().toString();
                String newW3 = ((EditText) findViewById(R.id.new_wrong3)).getText().toString();
                if(newA.equals("") || newQ.equals("") || newW1.equals("") || newW2.equals("") || newW3.equals("")){
                    Toast.makeText(getApplicationContext(), "All fields require text.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent changeMain = new Intent(AddCardActivity.this, MainActivity.class);
                    changeMain.putExtra("newQ", newQ);
                    changeMain.putExtra("newA", newA);
                    changeMain.putExtra("newW1", newW1);
                    changeMain.putExtra("newW2", newW2);
                    changeMain.putExtra("newW3", newW3);
                    changeMain.putExtra("editReturn",editStatus);
                    setResult(RESULT_OK, changeMain);
                    finish();
                }
            }
        });


    }
}