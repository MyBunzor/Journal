package com.example.wvand.journal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.security.KeyStore;

public class MainActivity extends AppCompatActivity {

    private EntryAdapter adapter;
    private EntryDatabase db;
    ListView listdays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get access to listview
        listdays = findViewById(R.id.ListView);

        // Set the listeners on the listview
        listdays.setOnItemClickListener(new ListViewClickListener());
        listdays.setOnItemLongClickListener(new ListViewLongClickListener());

        // Get database
        db = EntryDatabase.getInstance(getApplicationContext());

        // Get cursor, used to select the whole database so we can access data in it
        Cursor cursor = db.selectAll();

        // Set adapter on listview
        adapter = new EntryAdapter(this, R.layout.entry_row, cursor);
        listdays.setAdapter(adapter);
    }

    // Method connected to button for creating new report
    public void addDay(View view) {
        Intent newday = new Intent(MainActivity.this, InputActivity.class);
        startActivity(newday);
    }

    // When user clicks a day, redirection to specific day
    private class ListViewClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Get cursor that was clicked on which contains data
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);

            // Extract all data out of the cursor
            int id1 = cursor.getInt(cursor.getColumnIndex("_id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String mood = cursor.getString(cursor.getColumnIndex("mood"));
            String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));

            // Create JournalEntry object and pass it to intent
            JournalEntry clicked_day = new JournalEntry(id1, title, content, mood, timestamp);

            // If user clicks on item in list, redirection takes place to detailactivity
            Intent thisday = new Intent(MainActivity.this, DetailActivity.class);

            // Putting the concerning JournalEntry in the intent and starting it
            thisday.putExtra("clicked_day", clicked_day);
            startActivity(thisday);
        }
    }

    private class ListViewLongClickListener implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            // Getting access to the database
            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());

            // Find the entry that user clicked longer with cursor
            Cursor clicked = (Cursor) parent.getItemAtPosition(position);
            int index = clicked.getColumnIndex("_id");
            long id1 = clicked.getLong(index);

            // Delete the entry that was long clicked on
            db.delete(id1);

            // Call update method
            updateData();
            return true;
        }
    }

    private void updateData(){

        // Getting cursor
        Cursor cursor = db.selectAll();

        // Getting access to adapter
        adapter = new EntryAdapter(this, R.layout.entry_row, cursor);

        // Put in a new cursor for the updated data
        adapter.swapCursor(cursor);

        // Setting adapter again
        listdays.setAdapter(adapter);
    }

    // Method that gets called when returning to MainActivity
    @Override
    public void onResume(){
        super.onResume();

        // Updating added items
        updateData();
    }
}
