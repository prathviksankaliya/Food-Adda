package com.shadowtech.foodadda.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shadowtech.foodadda.Api.ApiUtilities;
import com.shadowtech.foodadda.Fragments.AddCartFragment;
import com.shadowtech.foodadda.Fragments.OrderFoodFragment;
import com.shadowtech.foodadda.Model.AddToCart;
import com.shadowtech.foodadda.Model.Responce;
import com.shadowtech.foodadda.R;
import com.shadowtech.foodadda.spf.SpfUserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.viewHolder> {

    Context context;
    List<AddToCart> addToCarts;

    public AddToCartAdapter(Context context, List<AddToCart> addToCarts) {
        this.context = context;
        this.addToCarts = addToCarts;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.addtocart_sample, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        AddToCart model = addToCarts.get(position);
        //Set Data using Model
        String Price = "₹ " + model.getTotal_price();
        holder.id.setText(String.valueOf(model.getId()));
        Glide.with(context).load(ApiUtilities.MenuItemImageUrl + model.getFood_img()).into(holder.image);
        holder.name.setText(model.getFood_name());
        holder.price.setText(Price);
        holder.quantity.setText(String.valueOf(model.getQuantity()));
        // Click -> Item Detail Screen
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpfUserData spfUserData = new SpfUserData(context);
                spfUserData.setSpfData(model.getFood_img(), model.getFood_name(), null, null, String.valueOf(model.getTotal_price()), null, 0, model.getQuantity(), model.getId(), 2);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frMainContainer, new OrderFoodFragment()).commit();
            }
        });
        // Long Press To Delete Cart Item
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete Order")
                        .setMessage("Are You Sure To Delete This Order ?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ApiUtilities.apiInterface().DeleteCartOrders(model.getId()).enqueue(new Callback<Responce>() {
                                    @Override
                                    public void onResponse(Call<Responce> call, Response<Responce> response) {
                                        Responce model = response.body();
                                        if (model != null) {
                                            if (model.getMessage().equals("fail")) {
                                                Toast.makeText(context, "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(context, "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                                                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                                                        .replace(R.id.frMainContainer, new AddCartFragment())
                                                        .addToBackStack(null)
                                                        .commit();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Responce> call, Throwable t) {
                                        Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return addToCarts.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        Button price;
        TextView name, quantity, id;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.igAddCartImage);
            price = itemView.findViewById(R.id.btnShowCartPrice);
            name = itemView.findViewById(R.id.txAddCartFoodName);
            quantity = itemView.findViewById(R.id.txShowQuantity);
            id = itemView.findViewById(R.id.txOrderId);
        }
    }
}
