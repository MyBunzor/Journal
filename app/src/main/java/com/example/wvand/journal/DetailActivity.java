package com.example.wvand.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Getting access to textviews
        TextView endtitle = findViewById(R.id.endTitle);
        TextView endcontent = findViewById(R.id.endContent);
        TextView endmood = findViewById(R.id.endMood);

        // Get intent, which has data with which we can set text
        Intent day = getIntent();
        JournalEntry retrievedEntry = (JournalEntry) day.getSerializableExtra("clicked_day");

        try {
            // Put data in string, to set textviews with
            String title = retrievedEntry.getTitle();
            String content = retrievedEntry.getContent();
            String mood = retrievedEntry.getMood();

            System.out.println(title);

            // Set text of textviews
            endtitle.setText(title);
            endcontent.setText(content);
            endmood.setText(mood);
        }
        catch(Exception e) {
            System.out.println("Hello" + e);


        }
    }

    // When user clicks 'backbutton', redirection takes place to MainActivity
    @Override
    public void onBackPressed() {
        Intent goback = new Intent(this, MainActivity.class);
        startActivity(goback);
    }
}
