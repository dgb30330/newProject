package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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
        View parent = findViewById(R.id.parent);
        final boolean[] answerVisible = {false};

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answerVisible[0]){
                    parent.setBackgroundColor(getResources().getColor(R.color.orange));
                    //System.out.println("answer invisible");
                    a.setVisibility(View.GONE);
                    q.setVisibility(View.VISIBLE);
                    answerVisible[0] = false;
                }
                else{
                    parent.setBackgroundColor(getResources().getColor(R.color.blue));
                    //System.out.println("else");
                    q.setVisibility(View.GONE);
                    a.setVisibility(View.VISIBLE);
                    answerVisible[0] = true;
                }

            }
        });
    }

}