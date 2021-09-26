package com.example.kylixmedusa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ArrayList<ReviewModel>list=new ArrayList<>();
    RVAdapter(Context ctx){
        this.context=ctx;
    }
    public void setItems(ArrayList<ReviewModel> rew){
        list.addAll(rew);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new ReviewVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        ReviewModel e = null;
        this.onBindViewHolder(holder,position,e);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position,ReviewModel e) {
        ReviewVH vh =(ReviewVH) holder;
        ReviewModel rew =e==null? list.get(position):e;
        vh.txt_name.setText(rew.getName());
        vh.txt_email.setText(rew.getEmail());
        vh.txt_des.setText(rew.getDescription());
        vh.txt_option.setOnClickListener(v->
        {
            PopupMenu popupMenu = new PopupMenu(context, vh.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item ->
            {
                switch (item.getItemId()) {
                    case R.id.menu_edit:
                        Intent intent = new Intent(context, Review.class);
                        intent.putExtra("EDIT", rew);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        AddEditReviewActivity dao = new AddEditReviewActivity();
                        dao.remove(rew.getKey()).addOnSuccessListener(suc ->
                        {
                            Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            list.remove(rew);
                        }).addOnFailureListener(er ->
                        {
                            Toast.makeText(context, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                        });

                        break;
                }
                return false;

            });
            popupMenu.show();
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
