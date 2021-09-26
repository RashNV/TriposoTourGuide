package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.hotelmanagement.AdapterClass.AllHotelAdapter;
import com.example.hotelmanagement.model.Hotel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AllHotelList extends AppCompatActivity {

    RecyclerView recyclerView;
    AllHotelAdapter allHotelAdapter;
    ImageView BackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_hotel_list);
        recyclerView = (RecyclerView) findViewById(R.id.recview);
        BackBtn = findViewById(R.id.backbtn);


        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goback = new Intent(getApplicationContext(), AddNewHotel.class);
                startActivity(goback);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Hotel> options =
                new FirebaseRecyclerOptions.Builder<Hotel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("HotelDetails"), Hotel.class)
                        .build();

        allHotelAdapter = new AllHotelAdapter(options);
        recyclerView.setAdapter(allHotelAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        allHotelAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        allHotelAdapter.stopListening();
    }
}