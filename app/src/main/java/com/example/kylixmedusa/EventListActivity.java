package com.example.kylixmedusa;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventListActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    DatabaseReference databaseReference;
    List<EventModel> list;
    Button add;
    EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        this.setTitle("Triposo");
        listView = findViewById(R.id.ksListView);
        databaseReference = FirebaseDatabase.getInstance().getReference("event");
        add = findViewById(R.id.newBtn);
        add.setOnClickListener(this);
        searchView = findViewById(R.id.searchView);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search(s.toString());
                System.out.println(s.toString());

            }
        });
        list = new ArrayList<>();
        if (getIntent().getBooleanExtra("isAdmin", true)) {
            add.setVisibility(View.VISIBLE);
        } else {
            add.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    list.add(dataSnapshot.getValue(EventModel.class));
                }

                EventArrayAdapter arrayAdapter = new EventArrayAdapter(EventListActivity.this, list, getIntent().getBooleanExtra("isAdmin", true));
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AddEditEventActivity.class);
        startActivity(intent);
    }

    private void search(String string) {


        Query query = databaseReference.orderByChild("name").startAt(string);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    list.add(dataSnapshot.getValue(EventModel.class));
                }

                EventArrayAdapter arrayAdapter = new EventArrayAdapter(EventListActivity.this, list, getIntent().getBooleanExtra("isAdmin", true));
                listView.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}