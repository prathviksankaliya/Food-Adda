package com.shadowtech.foodadda.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadowtech.foodadda.Model.AllMenuItems;
import com.shadowtech.foodadda.R;

import java.util.ArrayList;

public class AllMenuItemsAdapter extends RecyclerView.Adapter<AllMenuItemsAdapter.viewHolder> {

    Context context;
    ArrayList<AllMenuItems> allMenuItems;

    public AllMenuItemsAdapter(Context context, ArrayList<AllMenuItems> allMenuItems) {
        this.context = context;
        this.allMenuItems = allMenuItems;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.allmenuitems_sample , parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

    holder.image.setImageResource(allMenuItems.get(position).getAllitemimage());
    holder.name.setText(allMenuItems.get(position).getAllitemname());
    holder.special.setText(allMenuItems.get(position).getAllitemspecial());
    holder.rating.setText(allMenuItems.get(position).getAllitemrating());
    holder.price.setText(allMenuItems.get(position).getAllitemprice());
    }

    @Override
    public int getItemCount() {
        return allMenuItems.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name , special , rating ;
        Button price;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.igallitemsample);
            name = itemView.findViewById(R.id.txallitemnamesample);
            special = itemView.findViewById(R.id.txallitemspecialsample);
            rating = itemView.findViewById(R.id.txallitemratingsample);
            price = itemView.findViewById(R.id.btnallitemsample);
        }
    }
}
