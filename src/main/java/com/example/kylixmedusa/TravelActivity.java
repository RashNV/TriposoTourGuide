package com.example.kylixmedusa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class TravelActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        this.setTitle("Triposo");
        Button btn = findViewById(R.id.vtn1);
        Button btn2 = findViewById(R.id.vtn3);
        Button btn3 = findViewById(R.id.vtn2);
        Button btn_open = findViewById(R.id.vtn4);

        btn.setOnClickListener(v->{
            Intent intent = new Intent(this, TravelListActivity.class);
            intent.putExtra("isAdmin", getIntent().getBooleanExtra("isAdmin", true));
            startActivity(intent);
        });
        btn_open.setOnClickListener(v->{
            Intent intent = new Intent(this, Review.class);
            startActivity(intent);
        });
        btn2.setOnClickListener(v->{
            Intent intent = new Intent(this, EventActivity.class);
            startActivity(intent);
        });
        btn3.setOnClickListener(v->{
            Intent intent = new Intent(this,AllHotelList.class);
            startActivity(intent);
        });

        }

    }



