package com.example.wvand.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Time;

// Adjust the database here
public class InputActivity extends AppCompatActivity {

    String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // Find buttons and set the method for adding pictures
        Button great = findViewById(R.id.great);
        great.setOnClickListener(new moodClick());
        Button good = findViewById(R.id.good);
        good.setOnClickListener(new moodClick());
        Button bad = findViewById(R.id.bad);
        bad.setOnClickListener(new moodClick());
        Button horrible = findViewById(R.id.horrible);
        horrible.setOnClickListener(new moodClick());

        EditText inputtitle = findViewById(R.id.inputTitle);
        EditText inputcontent = findViewById(R.id.inputText);

        // Adding hints for user
        inputtitle.setHint("Title");
        inputcontent.setHint("What's happening?");
    }

    // Method for getting the mood
    private class moodClick implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            Button pressedButton = (Button) v;
            mood = pressedButton.getText().toString();
        }
    }

    public void addEntry(View view) {

        // Get access to the textviews in activity
        EditText inputtitle = findViewById(R.id.inputTitle);
        EditText inputcontent = findViewById(R.id.inputText);

        // Get what's inputted in textviews
        String title = String.valueOf(inputtitle.getText());
        String content = String.valueOf(inputcontent.getText());

        // Instantiate new entry
        JournalEntry entry = new JournalEntry(title, content, mood);

        // Using insert method to put given inputs in database
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        db.insert(entry);

        // When user submits entry, redirection to homescreen takes place
        Intent backtomain = new Intent(InputActivity.this, MainActivity.class);
        backtomain.putExtra("journalentry", entry);
        startActivity(backtomain);
    }
}