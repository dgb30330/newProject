package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView q = findViewById(R.id.flashcard_q);
        TextView a1 = findViewById(R.id.flashcard_a1);
        TextView a2 = findViewById(R.id.flashcard_a2);
        TextView a3 = findViewById(R.id.flashcard_a3);
        TextView a4 = findViewById(R.id.flashcard_a4);
        View parent = findViewById(R.id.parent);
        ImageView icon = findViewById(R.id.visible_icon);
        final boolean[] answerVisible = {false};

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a1.setBackground(getResources().getDrawable(R.drawable.card_background));
                a2.setVisibility(View.INVISIBLE);
                a4.setVisibility(View.INVISIBLE);
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a2.setBackground(getResources().getDrawable(R.drawable.card_background));
                a1.setVisibility(View.INVISIBLE);
                a4.setVisibility(View.INVISIBLE);
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a1.setVisibility(View.INVISIBLE);
                a2.setVisibility(View.INVISIBLE);
                a4.setVisibility(View.INVISIBLE);
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a4.setBackground(getResources().getDrawable(R.drawable.card_background));
                a1.setVisibility(View.INVISIBLE);
                a2.setVisibility(View.INVISIBLE);
            }
        });
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answerVisible[0]){
                    a1.setVisibility(View.INVISIBLE);
                    a2.setVisibility(View.INVISIBLE);
                    a3.setVisibility(View.INVISIBLE);
                    a4.setVisibility(View.INVISIBLE);
                    // also resetting backgrounds when hidden
                    a1.setBackground(getResources().getDrawable(R.drawable.answer_card));
                    a2.setBackground(getResources().getDrawable(R.drawable.answer_card));
                    a3.setBackground(getResources().getDrawable(R.drawable.answer_card));
                    a4.setBackground(getResources().getDrawable(R.drawable.answer_card));
                    icon.setImageResource(R.drawable.visible_yes);
                    answerVisible[0] = false;
                }
                else{
                    a1.setVisibility(View.VISIBLE);
                    a2.setVisibility(View.VISIBLE);
                    a3.setVisibility(View.VISIBLE);
                    a4.setVisibility(View.VISIBLE);
                    icon.setImageResource(R.drawable.visible_no);
                    answerVisible[0] = true;
                }

            }
        });
    }

}