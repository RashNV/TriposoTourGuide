package com.example.kylixmedusa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class Review extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        this.setTitle("Triposo");
        final EditText editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        final EditText editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);
        final EditText editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        Button TAdd1 = findViewById(R.id.button);
        Button btn_open = findViewById(R.id.button2);
        btn_open.setOnClickListener(v ->
        {
            Intent intent = new Intent(this, RVActivity.class);
            startActivity(intent);
        });
        AddEditReviewActivity reo = new AddEditReviewActivity();
        ReviewModel rew_edit = (ReviewModel) getIntent().getSerializableExtra("EDIT");
        if (rew_edit != null) {
            TAdd1.setText("UPDATE");
            editTextTextPersonName.setText(rew_edit.getName());
            editTextTextPersonName4.setText(rew_edit.getEmail());
            editTextTextMultiLine.setText(rew_edit.getDescription());
            btn_open.setVisibility(View.GONE);
        } else {
            TAdd1.setText("SUBMIT");
            btn_open.setVisibility(View.VISIBLE);
        }

        TAdd1.setOnClickListener(v -> {
            ReviewModel rew = new ReviewModel(editTextTextPersonName.getText().toString(), editTextTextPersonName4.getText().toString(), editTextTextMultiLine.getText().toString());
            if (rew_edit == null) {
                reo.add(rew).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Review Added", Toast.LENGTH_SHORT).show();

                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            } else {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name", editTextTextPersonName.getText().toString());
                hashMap.put("email", editTextTextPersonName4.getText().toString());
                hashMap.put("description", editTextTextMultiLine.getText().toString());
                reo.update(rew_edit.getKey(), hashMap).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Review Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }

        });

    }
}