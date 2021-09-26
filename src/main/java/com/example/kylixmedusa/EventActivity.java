package com.example.kylixmedusa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        this.setTitle("Triposo");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, EventListActivity.class);
        intent.putExtra("isAdmin", getIntent().getBooleanExtra("isAdmin", true));
        startActivity(intent);
    }
}