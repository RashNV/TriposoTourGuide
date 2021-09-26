package com.example.kylixmedusa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ViewTravelActivity extends AppCompatActivity implements View.OnClickListener {
    TextView des;
    TextView name;
    TextView location;
    TravelModel model;
    Button delete;
    Button edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_travel);
        name = findViewById(R.id.viewPName);
        des = findViewById(R.id.viewPDes);
        model = (TravelModel) getIntent().getSerializableExtra("object");
        name.setText(model.getName());
        des.setText(model.getDescription());

        location = findViewById(R.id.viewPloc);
        location.setText(model.getLocation());

        delete = findViewById(R.id.deleteBtn);
        edit = findViewById(R.id.modiBtn);
        delete.setOnClickListener(this);
        edit.setOnClickListener(this);
        ImageView imageView = findViewById(R.id.vixId);
        if (model.getPhoto() != null) {
            if (!model.getPhoto().isEmpty()) {
                Picasso.get().load(model.getPhoto()).into(imageView);
            }
        }
        if (getIntent().getBooleanExtra("isAdmin", true)) {
            delete.setVisibility(View.VISIBLE);
            edit.setVisibility(View.VISIBLE);
        } else {
            delete.setVisibility(View.GONE);
            edit.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.deleteBtn) {
            FirebaseDatabase.getInstance().getReference("travel").child(model.getId()).removeValue();
            Toast.makeText(this, "Place Delete", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Intent intent = new Intent(this, AddEditTravelActivity.class);
            intent.putExtra("object", model);
            startActivity(intent);
            finish();
        }

    }
}