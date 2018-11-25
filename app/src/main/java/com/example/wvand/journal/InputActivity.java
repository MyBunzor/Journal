package com.example.wvand.journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void submitDay(View view) {

        System.out.println("Still to submit!");
    }
}
