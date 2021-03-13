package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    Flashcard currentCard;
    int currentCardDisplayedIndex = 0;
    boolean isEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());

        allFlashcards = flashcardDatabase.getAllCards();
        if(allFlashcards.size()>0) {
            currentCardDisplayedIndex = getRandom(0,allFlashcards.size()-1);
            currentCard = allFlashcards.get(currentCardDisplayedIndex);
        }
        else{
            currentCard = new Flashcard("What is the population of Palm Beach County",
                    "1,500,000","750,000","1,000,000","2,250,000");
            allFlashcards.add(currentCard);
        }

        TextView q = findViewById(R.id.flashcard_q);
        q.setText(currentCard.getQuestion());
        TextView a1 = findViewById(R.id.flashcard_a1);
        a1.setText(currentCard.getWrongAnswer1());
        TextView a2 = findViewById(R.id.flashcard_a2);
        a2.setText(currentCard.getWrongAnswer2());
        // a3 is correct answer in start state
        TextView a3 = findViewById(R.id.flashcard_a3);
        a3.setText(currentCard.getAnswer());
        TextView a4 = findViewById(R.id.flashcard_a4);
        a4.setText(currentCard.getWrongAnswer3());
        View parent = findViewById(R.id.parent);
        ImageView icon = findViewById(R.id.visible_icon);
        ImageView newActivity = findViewById(R.id.new_activity);
        ImageView editActivity = findViewById(R.id.edit_activity);
        ImageView next = findViewById(R.id.next_button);
        ImageView delete = findViewById(R.id.delete_button);

        Intent nextActivity = new Intent(MainActivity.this,AddCardActivity.class);
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
        newActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity.putExtra("preQ", "none");
                nextActivity.putExtra("preA", "none");
                nextActivity.putExtra("wrong1", "none");
                nextActivity.putExtra("wrong2", "none");
                nextActivity.putExtra("wrong3", "none");
                isEdit = false;
                nextActivity.putExtra("editStatus",isEdit);
                MainActivity.this.startActivityForResult(nextActivity, 100);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                currentCardDisplayedIndex+=1;
                if(currentCardDisplayedIndex >= allFlashcards.size()){
                    currentCardDisplayedIndex = 0;
                }*/
                if( allFlashcards.size()>1) {
                    int indexToChange = currentCardDisplayedIndex;
                    while (indexToChange == currentCardDisplayedIndex) {
                        currentCardDisplayedIndex = getRandom(0, allFlashcards.size());
                    }
                }

                currentCard = allFlashcards.get(currentCardDisplayedIndex);
                q.setText(currentCard.getQuestion());
                a1.setText(currentCard.getWrongAnswer1());
                a2.setText(currentCard.getWrongAnswer2());
                a3.setText(currentCard.getAnswer());
                a4.setText(currentCard.getWrongAnswer3());
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allFlashcards.remove(currentCard);
                flashcardDatabase.deleteCard(currentCard.getQuestion());
                currentCardDisplayedIndex-=1;
                if(allFlashcards.size()==0){
                    currentCard = null;
                    q.setText("");
                    a1.setText("");
                    a2.setText("");
                    a3.setText("");
                    a4.setText("");
                    currentCardDisplayedIndex = 0;
                }
                else{
                    if(currentCardDisplayedIndex < 0){
                        currentCardDisplayedIndex = allFlashcards.size()-1;}
                    currentCard = allFlashcards.get(currentCardDisplayedIndex);
                    q.setText(currentCard.getQuestion());
                    a1.setText(currentCard.getWrongAnswer1());
                    a2.setText(currentCard.getWrongAnswer2());
                    a3.setText(currentCard.getAnswer());
                    a4.setText(currentCard.getWrongAnswer3());
                    }

            }
        });
        editActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passQ = q.getText().toString();
                String passA = a3.getText().toString();
                String w1 = a1.getText().toString();
                String w2 = a2.getText().toString();
                String w3 = a4.getText().toString();
                nextActivity.putExtra("preQ", passQ);
                nextActivity.putExtra("preA", passA);
                nextActivity.putExtra("wrong1", w1);
                nextActivity.putExtra("wrong2", w2);
                nextActivity.putExtra("wrong3", w3);
                isEdit = true;
                nextActivity.putExtra("editStatus",isEdit);
                MainActivity.this.startActivityForResult(nextActivity, 100);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent changeMain) {
        super.onActivityResult(requestCode, resultCode, changeMain);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                //converts new data
                String newQ = changeMain.getExtras().getString("newQ");
                String newA = changeMain.getExtras().getString("newA");
                String newW1 = changeMain.getExtras().getString("newW1");
                String newW2 = changeMain.getExtras().getString("newW2");
                String newW3 = changeMain.getExtras().getString("newW3");

                // sets up for database if edited v created
                if(changeMain.getExtras().getBoolean("editReturn")){
                    currentCard.setAnswer(newA);
                    currentCard.setQuestion(newQ);
                    currentCard.setWrongAnswer1(newW1);
                    currentCard.setWrongAnswer2(newW2);
                    currentCard.setWrongAnswer3(newW3);
                    flashcardDatabase.updateCard(currentCard);
                }
                else {
                    Flashcard createdCard = new Flashcard(newQ, newA, newW1, newW2, newW3);
                    flashcardDatabase.insertCard(createdCard);
                    currentCard = createdCard;
                    allFlashcards = flashcardDatabase.getAllCards();

                }
                // gets views for changes

                TextView q = findViewById(R.id.flashcard_q);
                TextView a1 = findViewById(R.id.flashcard_a1);
                TextView a2 = findViewById(R.id.flashcard_a2);
                TextView a3 = findViewById(R.id.flashcard_a3);
                TextView a4 = findViewById(R.id.flashcard_a4);
                ImageView icon = findViewById(R.id.visible_icon);

                //changes made
                q.setText(currentCard.getQuestion());
                q.setTag("change");
                a3.setText(currentCard.getAnswer());
                a1.setText(currentCard.getWrongAnswer1());
                a2.setText(currentCard.getWrongAnswer2());
                a4.setText(currentCard.getWrongAnswer3());

                // this just in case answers visible on change.
                a1.setVisibility(View.INVISIBLE);
                a2.setVisibility(View.INVISIBLE);
                a3.setVisibility(View.INVISIBLE);
                a4.setVisibility(View.INVISIBLE);
                icon.setImageResource(R.drawable.visible_yes);

                Snackbar.make(findViewById(R.id.parent), "New card created!", Snackbar.LENGTH_INDEFINITE).setDuration(1200).show();

            }
        }
    }

    protected int getRandom(int min, int max){
        int result = min + (int)(Math.random() * max);
        return result;
    }

}