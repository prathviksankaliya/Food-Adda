package com.shadowtech.foodadda.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadowtech.foodadda.Model.Recommeneditems;
import com.shadowtech.foodadda.R;

import java.util.ArrayList;

public class RecommenedItemAdapter extends RecyclerView.Adapter<RecommenedItemAdapter.viewHolder> {
    Context context;
    ArrayList<Recommeneditems> recommeneditems;

    public RecommenedItemAdapter(Context context, ArrayList<Recommeneditems> recommeneditems) {
        this.context = context;
        this.recommeneditems = recommeneditems;
    }

    public RecommenedItemAdapter() {
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.recommeneditem_sample ,  parent , false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    Recommeneditems model = recommeneditems.get(position);
    holder.itemimage.setImageResource(model.getImage());
    holder.itemname.setText(model.getItemname());
    holder.itemrating.setText(model.getRating());;
    holder.price.setText(model.getPrice());
    }

    @Override
    public int getItemCount() {
        return recommeneditems.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView itemimage;
        TextView itemname , itemrating ;
        Button price;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemimage= itemView.findViewById(R.id.igItemImage);
            itemname = itemView.findViewById(R.id.txitemnamesample);
            itemrating = itemView.findViewById(R.id.txitemratingsample);
            price = itemView.findViewById(R.id.btnpricesample);
        }
    }
}
