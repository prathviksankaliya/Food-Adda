package com.shadowtech.foodadda.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.shadowtech.foodadda.Db.DbHelper;
import com.shadowtech.foodadda.Fragments.OrderFoodFragment;
import com.shadowtech.foodadda.Model.AddToCart;
import com.shadowtech.foodadda.R;

import java.util.ArrayList;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.viewHolder> {
    Context context;
    ArrayList<AddToCart> addToCarts;

    public AddToCartAdapter(Context context, ArrayList<AddToCart> addToCarts) {
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
        String Price = "₹ " + model.getTotalPrice();

        holder.id.setText("" + model.getId());
        holder.image.setImageResource(model.getImage());
        holder.name.setText(model.getName());
        holder.price.setText(Price);
        holder.quantity.setText("" + model.getQuantity());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences spf = context.getSharedPreferences("AllMenuItemsDetail", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = spf.edit();
                editor.putInt("id", model.getId());
                editor.putInt("Update", 2);
                editor.apply();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frMainContainer, new OrderFoodFragment()).commit();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DbHelper dbHelper = new DbHelper(context);
                new AlertDialog.Builder(context)
                        .setTitle("Delete Order")
                        .setMessage("Are You Sure To Delete This Order ?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (dbHelper.DeleteOrder(model.getId()) > 0) {
                                    Toast.makeText(context, "Order Delete SuccessFully .", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Order Deleted Failed !!! ", Toast.LENGTH_SHORT).show();
                                }
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
