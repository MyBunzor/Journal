package com.example.wvand.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {

    // Attributes for a day
    private int id;
    private String title;
    private String content;
    // private ... mood;
    // private ... timestamp;

    public JournalEntry(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
