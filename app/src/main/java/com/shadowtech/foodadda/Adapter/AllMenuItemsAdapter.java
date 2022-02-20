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

import com.bumptech.glide.Glide;
import com.shadowtech.foodadda.Api.ApiUtilities;
import com.shadowtech.foodadda.Fragments.OrderFoodFragment;
import com.shadowtech.foodadda.Model.AllMenuItems;
import com.shadowtech.foodadda.R;
import com.shadowtech.foodadda.databinding.AllmenuitemsSampleBinding;
import com.shadowtech.foodadda.spf.SpfUserData;

import java.util.ArrayList;
import java.util.List;

public class AllMenuItemsAdapter extends RecyclerView.Adapter<AllMenuItemsAdapter.viewHolder> {

    Context context;
    List<AllMenuItems> Model;
    SpfUserData spf;

    public AllMenuItemsAdapter(Context context, List<AllMenuItems> Model) {
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
        String Price = "₹ " + allMenuItems.getPrice();
        Glide.with(context).load(ApiUtilities.MenuItemImageUrl+allMenuItems.getImg()).into(holder.binding.igAddCartImage);
        holder.binding.txAddCartFoodName.setText(allMenuItems.getName());
        holder.binding.txallitemspecialsample.setText(allMenuItems.getType());
        holder.binding.txallitemratingsample.setText(String.valueOf(allMenuItems.getRating()));
        holder.binding.btnCartPrice.setText(Price);
        holder.binding.txDelivery.setText(allMenuItems.getDelivery());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spf = new SpfUserData(context);
                spf.setSpfData(allMenuItems.getImg(), allMenuItems.getName(), allMenuItems.getType(), String.valueOf(allMenuItems.getRating()), allMenuItems.getPrice(), allMenuItems.getDelivery(), 1,0,0,0);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frMainContainer, new OrderFoodFragment()).commit();
            }
        });

        // Click -> Item Detail Screen
        holder.binding.btnCartPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spf = new SpfUserData(context);
                spf.setSpfData(allMenuItems.getImg(), allMenuItems.getName(), allMenuItems.getType(), String.valueOf(allMenuItems.getRating()), allMenuItems.getPrice(), allMenuItems.getDelivery(), 1, 0,0 ,0);
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

        AllmenuitemsSampleBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = AllmenuitemsSampleBinding.bind(itemView);

        }
    }
}
