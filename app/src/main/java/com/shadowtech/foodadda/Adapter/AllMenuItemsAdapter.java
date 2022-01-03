package com.shadowtech.foodadda.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.shadowtech.foodadda.Fragments.OrderFoodFragment;
import com.shadowtech.foodadda.Model.AllMenuItems;
import com.shadowtech.foodadda.R;

import java.util.ArrayList;

public class AllMenuItemsAdapter extends RecyclerView.Adapter<AllMenuItemsAdapter.viewHolder> {

    Context context;
    ArrayList<AllMenuItems> Model;

    public AllMenuItemsAdapter(Context context, ArrayList<AllMenuItems> Model) {
        this.context = context;
        this.Model = Model;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.allmenuitems_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        AllMenuItems allMenuItems = Model.get(position);
        String Price = "₹ " + allMenuItems.getAllitemprice();
        holder.image.setImageResource(allMenuItems.getAllitemimage());
        holder.name.setText(allMenuItems.getAllitemname());
        holder.special.setText(allMenuItems.getAllitemspecial());
        holder.rating.setText(allMenuItems.getAllitemrating());
        holder.price.setText(Price);
        holder.delivery.setText(allMenuItems.getDelivery());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences spf = context.getSharedPreferences("AllMenuItemsDetail", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = spf.edit();
                editor.putInt("Image", allMenuItems.getAllitemimage());
                editor.putString("Name", allMenuItems.getAllitemname());
                editor.putString("Special", allMenuItems.getAllitemspecial());
                editor.putString("Rating", allMenuItems.getAllitemrating());
                editor.putString("Price", allMenuItems.getAllitemprice());
                editor.putString("Delivery", allMenuItems.getDelivery());
                editor.putInt("Insert", 1);
                editor.apply();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frMainContainer, new OrderFoodFragment()).commit();
            }
        });
        holder.price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences spf = context.getSharedPreferences("AllMenuItemsDetail", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = spf.edit();
                editor.putInt("Image", allMenuItems.getAllitemimage());
                editor.putString("Name", allMenuItems.getAllitemname());
                editor.putString("Special", allMenuItems.getAllitemspecial());
                editor.putString("Rating", allMenuItems.getAllitemrating());
                editor.putString("Price", allMenuItems.getAllitemprice());
                editor.putString("Delivery", allMenuItems.getDelivery());
                editor.putInt("Insert", 1);
                editor.apply();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frMainContainer, new OrderFoodFragment()).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Model.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, special, rating, delivery;
        Button price;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.igAddCartImage);
            name = itemView.findViewById(R.id.txAddCartFoodName);
            special = itemView.findViewById(R.id.txallitemspecialsample);
            rating = itemView.findViewById(R.id.txallitemratingsample);
            price = itemView.findViewById(R.id.btnCartPrice);
            delivery = itemView.findViewById(R.id.txDelivery);
        }
    }
}
