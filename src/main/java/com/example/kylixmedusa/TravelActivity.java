package com.example.kylixmedusa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TravelActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, TravelListActivity.class);
        intent.putExtra("isAdmin", getIntent().getBooleanExtra("isAdmin", true));
        startActivity(intent);
    }
}