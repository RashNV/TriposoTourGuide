package com.example.kylixmedusa.AdapterClass;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kylixmedusa.R;
import com.example.kylixmedusa.model.Hotel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class AllHotelAdapter extends FirebaseRecyclerAdapter<Hotel, AllHotelAdapter.myviewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AllHotelAdapter(@NonNull FirebaseRecyclerOptions<Hotel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull Hotel model) {
        holder.hotelName.setText(model.getHotelName());
        holder.hotelType.setText(model.getType());
        holder.location.setText(model.getDistrict());

            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final DialogPlus dialogPlus = DialogPlus.newDialog(holder.hotelName.getContext())
                            .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                            .setExpanded(true, 1100)
                            .create();

                    View myview = dialogPlus.getHolderView();
                    final EditText hotelName = myview.findViewById(R.id.uname);
                    final EditText district = myview.findViewById(R.id.vc1);
                    final EditText type = myview.findViewById(R.id.vc2);
                    final EditText address = myview.findViewById(R.id.vc3);
                    final EditText price = myview.findViewById(R.id.vc4);
                    final EditText contact = myview.findViewById(R.id.vc5);
                    Button submit = myview.findViewById(R.id.usubmit);

                    hotelName.setText(model.getHotelName());
                    district.setText(model.getDistrict());
                    type.setText(model.getType());
                    address.setText(model.getAddress());
                    price.setText(model.getPrice());
                    contact.setText(model.getContact());

                    dialogPlus.show();

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("hotelName", hotelName.getText().toString());
                            map.put("district", district.getText().toString());
                            map.put("type", type.getText().toString());
                            map.put("address", address.getText().toString());
                            map.put("price", price.getText().toString());
                            map.put("contact", contact.getText().toString());

                            FirebaseDatabase.getInstance().getReference().child("HotelDetails")
                                    .child(getRef(position).getKey()).updateChildren(map)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            dialogPlus.dismiss();
                                            Toast.makeText(myview.getContext(), "Update Successfully.", Toast.LENGTH_SHORT).show();

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            dialogPlus.dismiss();
                                        }
                                    });
                        }
                    });


                }
            });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.hotelName.getContext());
                builder.setTitle("Delete Hotel");
                builder.setMessage("Do you want to delete this hotel permanently?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("HotelDetails")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView edit, delete;
        TextView hotelName, hotelType, location;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            hotelName = (TextView) itemView.findViewById(R.id.t1);
            hotelType = (TextView) itemView.findViewById(R.id.t2);
            location = (TextView) itemView.findViewById(R.id.t4);

            edit = (TextView) itemView.findViewById(R.id.editicon);
            delete = (TextView) itemView.findViewById(R.id.deleteicon);
        }
    }


}
