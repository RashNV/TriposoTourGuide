package com.example.kylixmedusa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class TravelArrayAdapter extends ArrayAdapter<TravelModel> implements View.OnClickListener {
    private List<TravelModel> list;
    private TravelModel model;
    private boolean isAdmin;


    public TravelArrayAdapter(@NonNull Context context, List<TravelModel> list, boolean isAdmin) {
        super(context, R.layout.list_tile_layout, list);
        this.list = list;
        this.isAdmin = isAdmin;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        model = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.list_tile_layout, parent, false);
        TextView name = convertView.findViewById(R.id.viewplceName);
        Button see = convertView.findViewById(R.id.vtn2);
        ImageView imageView = convertView.findViewById(R.id.viewImage);
        if (model.getPhoto() != null) {
            if (!model.getPhoto().isEmpty()) {
                Picasso.get().load(model.getPhoto()).into(imageView);
            }
        }
        see.setOnClickListener(this);
        name.setText(model.getName());
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), ViewTravelActivity.class);
        intent.putExtra("object", model);
        intent.putExtra("isAdmin", isAdmin);
        getContext().startActivity(intent);

    }
}
