package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView q = findViewById(R.id.flashcard_q);
        TextView a = findViewById(R.id.flashcard_a);
        final boolean[] answerVisible = {false};

        findViewById(R.id.parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answerVisible[0]){
                    System.out.println("answer invisible");
                    a.setVisibility(View.GONE);
                    q.setVisibility(View.VISIBLE);
                    answerVisible[0] = false;
                }
                else{

                    System.out.println("else");
                    q.setVisibility(View.GONE);
                    a.setVisibility(View.VISIBLE);
                    answerVisible[0] = true;
                }

            }
        });
    }

}