package com.example.kylixmedusa;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewVH extends RecyclerView.ViewHolder {

    public TextView txt_name,txt_email,txt_des,txt_option;
    public ReviewVH(@NonNull View itemView) {
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_email = itemView.findViewById(R.id.txt_email);
        txt_des = itemView.findViewById(R.id.txt_des);
        txt_option = itemView.findViewById(R.id.txt_option);
    }
}
