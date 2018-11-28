package com.example.wvand.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    private EntryDatabase(Context context, String name, int version) {
        super(context, name, null, version);
    }

    // Static, because we only want one instance of the database
    private static EntryDatabase instance;

    // Check if a database already exists, if so return it, if not make a new one
    public static EntryDatabase getInstance(Context context){
        if(instance == null) {
            instance = new EntryDatabase(context, "instance", 1);
            return instance;
        }
        else {
            return instance;
        }
    }

    // Cursor to select everything
    public Cursor selectAll() {

        // Open up connection with database
        SQLiteDatabase writabledb = instance.getWritableDatabase();

        // Select everything and return it
        Cursor cursor = writabledb.rawQuery("SELECT * FROM entries", null);
        return cursor;
    }

    // Creating a database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table entries (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, mood TEXT, timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");

        // Adding sample items
        db.execSQL("INSERT INTO entries (_id, title, content, mood, timestamp) VALUES(1, 'monday', 'great day', 'super', 1)");
        db.execSQL("INSERT INTO entries (_id, title, content, mood, timestamp) VALUES(2, 'tuesday', 'bad day', 'horrible', 2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "entries");
        onCreate(db);
    }

    // Insert method receives parameters from InputActivity
    public void insert(JournalEntry entry){

        // Open up connection with database
        SQLiteDatabase writabledb = instance.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        // Add values from inputactivity and put them in contentvalues
        contentValues.put("title", entry.getTitle());
        contentValues.put("content", entry.getContent());
        contentValues.put("mood", entry.getMood());

        // Insert the contentvalues in the database
        writabledb.insert("entries", null, contentValues);
    }

    public void delete(long id){

        // Get access to the database
        SQLiteDatabase removedb = instance.getWritableDatabase();

        // Remove everything where id = ?
        removedb.delete("entries", "_id = ?", new String[]{String.valueOf(id)});
    }
}