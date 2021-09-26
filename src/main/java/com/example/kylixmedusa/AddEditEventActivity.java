package com.example.kylixmedusa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AddEditEventActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference databaseReference;
    Button uploadBtn;
    Button addBtn;
    EditText name;
    EditText cName;
    EditText phne;
    EditText price;
    EditText description;
    EditText email;
    EventModel model;
    boolean add = true;
    String p;
    String c;

    Uri filePath;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;


    FirebaseStorage storage;
    StorageReference storageReference;
    private Spinner spinner, spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        uploadBtn = findViewById(R.id.addEventUploadBtn);
        uploadBtn.setOnClickListener(this);
        addBtn = findViewById(R.id.addEventBtn);
        addBtn.setOnClickListener(this);

        name = findViewById(R.id.EventName);
        cName = findViewById(R.id.Cname);
        phne = findViewById(R.id.phone);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        email = findViewById(R.id.email);
        databaseReference = FirebaseDatabase.getInstance().getReference("event");
        if (getIntent().hasExtra("object")) {
            model = (EventModel) getIntent().getSerializableExtra("object");
            name.setText(model.getName());
            cName.setText(model.getcName());
            phne.setText(model.getPhone());
            description.setText(model.getDescription());
            email.setText(model.getEmail());
            price.setText(model.getPrice());
            add = false;
        } else {
            add = true;
            model = null;

        }

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        spinner = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Province, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence>adapter1 = ArrayAdapter.createFromResource(this,R.array.City, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter1);


    }

    // Select Image method
    private void SelectImage() {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    // UploadImage method
    private void uploadImage() {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();


            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child("images/" + UUID.randomUUID().toString());

            ref.putFile(filePath)
                    .addOnSuccessListener(taskSnapshot -> {
                        progressDialog.dismiss();
                        Toast.makeText(AddEditEventActivity.this,
                                "Image Uploaded!!",
                                Toast.LENGTH_SHORT)
                                .show();
                        taskSnapshot
                                .getStorage()
                                .getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener() {

                                    @Override
                                    public void onSuccess(Object o) {
                                        model.setPhoto(((Uri) o).toString());
                                        databaseReference.child(model.getId()).setValue(model);
                                        Toast.makeText(AddEditEventActivity.this, add ? "Event Add" : "Event Update", Toast.LENGTH_LONG).show();
                                        finish();
                                    }


                                });

                    })

                    .addOnFailureListener(e -> {

                        // Error, Image not uploaded
                        progressDialog.dismiss();
                        Toast
                                .makeText(AddEditEventActivity.this,
                                        "Failed " + e.getMessage(),
                                        Toast.LENGTH_SHORT)
                                .show();
                        finish();
                    })
                    .addOnProgressListener(
                            taskSnapshot -> {
                                double progress
                                        = (100.0
                                        * taskSnapshot.getBytesTransferred()
                                        / taskSnapshot.getTotalByteCount());
                                progressDialog.setMessage(
                                        "Uploaded "
                                                + (int) progress + "%");
                            });
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == addBtn.getId()) {
            if (add) {
                model = new EventModel(databaseReference.push().getKey(), name.getText().toString(), spinner.getSelectedItem().toString(), spinner2.getSelectedItem().toString(), cName.getText().toString(), phne.getText().toString(), email.getText().toString(), price.getText().toString(),  description.getText().toString());
            } else {
                model.update(name.getText().toString(), spinner.getSelectedItem().toString(), spinner2.getSelectedItem().toString(), cName.getText().toString(), phne.getText().toString(), email.getText().toString(), price.getText().toString(), description.getText().toString());
            }
            uploadImage();

            if (filePath == null) {

                databaseReference.child(model.getId()).setValue(model);
                Toast.makeText(AddEditEventActivity.this, add ? "Event Add" : "Event Update", Toast.LENGTH_LONG).show();
                finish();

            }
        } else {
            SelectImage();
        }
    }


}