package com.example.wvand.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, int layout, Cursor c) {
        super(context, layout, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Retrieving title with cursor and setting it
        TextView title = view.findViewById(R.id.title);
        String titles = cursor.getString(cursor.getColumnIndex("title"));
        title.setText(titles);

        // Retrieving the timestamp and setting it
        TextView timestamp = view.findViewById(R.id.timestamp);
        String timestamps = cursor.getString(cursor.getColumnIndex("timestamp"));
        timestamp.setText(timestamps);

        // Retrieving the mood and setting it
        TextView mood = view.findViewById(R.id.mood);
        String moods = cursor.getString(cursor.getColumnIndex("mood"));
        mood.setText(moods);

        // To do: with mood, set a picture as well
    }
}
