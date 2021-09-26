package com.example.kylixmedusa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kylixmedusa.model.Hotel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddNewHotel extends AppCompatActivity {

    public static final String HOTEL_PREFIX = "HM-00";

    TextView HotelName, District, Type, Address, Price, Contact;
    ImageView GOBack;

    int ID = 0;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_hotel);
        this.setTitle("Triposo");

        HotelName = findViewById(R.id.t1);
        District = findViewById(R.id.t2);
        Type = findViewById(R.id.t3);
        Address = findViewById(R.id.t4);
        Price = findViewById(R.id.t5);
        Contact = findViewById(R.id.t6);
        GOBack = findViewById(R.id.backbtn);




        GOBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(AddNewHotel.this, AllHotelList.class);
                startActivity(go);
            }
        });

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("HotelDetails");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ID = (int) snapshot.getChildrenCount();
                System.out.println(ID);
                ID = ID + 1;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void AddHotel(View view) {

        String cName = HotelName.getText().toString();
        String district = District.getText().toString();
        String type = Type.getText().toString();
        String address = Address.getText().toString();
        String price = Price.getText().toString();
        String contact = Contact.getText().toString();

        if (cName.isEmpty()) {
            HotelName.setError("Hotel name can not be empty.");
        }
        if (district.isEmpty()) {
            District.setError("District can not be empty.");
        }
        if (type.isEmpty()) {
            Type.setError("Type can not be empty.");
        }
        if (address.isEmpty()) {
            Address.setError("Address can not be empty.");
        }
        if (price.isEmpty()) {
            Price.setError("Price can not be empty.");
        }
        if (contact.isEmpty()) {
            Contact.setError("Contact number can not be empty.");
        }
        if (!type.matches("7") && !type.matches("5")) {
            Type.setError("Enter valid hotel type. It's must be 7 or 5.");
        }
        else {

            String id = HOTEL_PREFIX + ID;
            Hotel hotel = new Hotel(id, cName, district, type, address, price, contact);
            reference.child(id).setValue(hotel);
            Toast.makeText(AddNewHotel.this, "Hotel is created successfully.", Toast.LENGTH_SHORT).show();
            Intent refresh = new Intent(getApplicationContext(), AddNewHotel.class);
            startActivity(refresh);
        }
    }
}