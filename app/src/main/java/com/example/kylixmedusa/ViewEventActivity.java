package com.example.kylixmedusa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ViewEventActivity extends AppCompatActivity implements View.OnClickListener {
    TextView contact;
    TextView des;
    TextView location;
    TextView price;
    TextView name;
    EventModel model;
    Button delete;
    Button edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        this.setTitle("Triposo");
        name = findViewById(R.id.viewEName);
        contact = findViewById(R.id.viewECont);
        des = findViewById(R.id.viewEDes);
        location = findViewById(R.id.viewELoc);
        price = findViewById(R.id.viewEPrice);
        model = (EventModel) getIntent().getSerializableExtra("object");
        name.setText(model.getName());
        contact.setText(model.getcName() + "," + model.getPhone() + "," + model.getEmail());
        des.setText(model.getDescription());
        location.setText(model.getProvince() + ", " + model.getCity());
        price.setText("Rs : " + model.getPrice());

        delete = findViewById(R.id.deleteBtn);
        edit = findViewById(R.id.modiBtn);
        delete.setOnClickListener(this);
        edit.setOnClickListener(this);
        ImageView imageView = findViewById(R.id.vixId);
        if(model.getPhoto()!=null){
            if (!model.getPhoto().isEmpty()){
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
            FirebaseDatabase.getInstance().getReference("event").child(model.getId()).removeValue();
            Toast.makeText(this, "Event Delete", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Intent intent = new Intent(this, AddEditEventActivity.class);
            intent.putExtra("object", model);
            startActivity(intent);
            finish();
        }

    }
}