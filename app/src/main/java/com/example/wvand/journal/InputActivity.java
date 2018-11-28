package com.example.wvand.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;

// Adjust the database here
public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        EditText inputtitle = findViewById(R.id.inputTitle);
        EditText inputcontent = findViewById(R.id.inputText);
        EditText inputmood = findViewById(R.id.inputMood);

        // Adding hints for user
        inputtitle.setHint("Title");
        inputcontent.setHint("What's happening?");
        inputmood.setHint("How do you feel?");
    }

    public void addEntry(View view) {

        try {

            // Get access to the textviews in activity
            EditText inputtitle = findViewById(R.id.inputTitle);
            EditText inputcontent = findViewById(R.id.inputText);
            EditText inputmood = findViewById(R.id.inputMood);

            // Get what's inputted in textviews
            String title = String.valueOf(inputtitle.getText());
            String content = String.valueOf(inputcontent.getText());
            String mood = String.valueOf(inputmood.getText());

            JournalEntry entry = new JournalEntry(title, content, mood);

            // Using insert method to put given inputs in database
            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
            db.insert(entry);

            // When user submits entry, redirection to homescreen takes place
            Intent backtomain = new Intent(InputActivity.this, MainActivity.class);
            startActivity(backtomain);
        }
        catch(Exception e) {
            System.out.println("Hello" + e);
        }


    }
}
