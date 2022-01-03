package com.shadowtech.foodadda.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.shadowtech.foodadda.Db.DbHelper;
import com.shadowtech.foodadda.Model.AddToCart;
import com.shadowtech.foodadda.Model.ConfirmOrder;
import com.shadowtech.foodadda.R;
import com.shadowtech.foodadda.databinding.FragmentOrderFoodBinding;

import java.util.ArrayList;

public class OrderFoodFragment extends Fragment {

    public OrderFoodFragment() {
        // Required empty public constructor
    }

    FragmentOrderFoodBinding binding;
    int Count = 1;
    int TotlePrice, FoodPrice, Quantity, UpdateTotalPrice;
    DbHelper dbHelper;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderFoodBinding.inflate(getLayoutInflater());
        dbHelper = new DbHelper(getContext());
        ArrayList<AddToCart> addToCarts = dbHelper.AddToCartShowOrders();
        if (addToCarts.isEmpty()) {
            binding.igAddCartOrder.setImageResource(R.drawable.bag);
        } else {
            binding.igAddCartOrder.setImageResource(R.drawable.fillbag);
        }
        binding.igBackDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frMainContainer, new DashboardFragment());
                fragmentTransaction.commit();
            }
        });
        SharedPreferences spf = requireContext().getSharedPreferences("AllMenuItemsDetail", Context.MODE_PRIVATE);
        int Image = spf.getInt("Image", 0);
        String Name = spf.getString("Name", null);
        String Special = spf.getString("Special", null);
        String Rating = spf.getString("Rating", null);
        String Price = spf.getString("Price", null);
        String Delivery = spf.getString("Delivery", null);
        int InsertType = spf.getInt("Insert", 0);
        int UpdateType = spf.getInt("Update", 0);

        if (InsertType == 1) {
            SharedPreferences.Editor editor = spf.edit();
            editor.remove("Insert");
            editor.apply();
            binding.igOrderFood.setImageResource(Image);
            binding.txOrderFoodName.setText(Name);
            binding.txOrderFoodRating.setText(Rating);
            binding.txOrderFoodPrice.setText("₹ " + Price);
            binding.txTotalFoodPrice.setText("₹ " + Price);

            FoodPrice = Integer.parseInt(Price);

            binding.btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Count >= 1) {
                        Count++;
                        binding.txCount.setText("" + Count);

                        TotlePrice = FoodPrice * Count;

                    } else {
                        Count = 1;
                        binding.txCount.setText("" + Count);
                        TotlePrice = FoodPrice;
                    }
                    binding.txTotalFoodPrice.setText("₹ " + TotlePrice);
                }
            });
            binding.btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Count > 1) {
                        Count--;
                        binding.txCount.setText("" + Count);
                        TotlePrice = FoodPrice * Count;
                    } else {
                        Count = 1;
                        binding.txCount.setText("" + Count);
                        TotlePrice = FoodPrice;
                    }
                    binding.txTotalFoodPrice.setText("₹ " + TotlePrice);
                }
            });

            binding.btnOrderAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TotlePrice == 0) {
                        TotlePrice = FoodPrice;
                    }
                    if (binding.edOrderFullName.getText().toString().isEmpty()) {
                        binding.edOrderFullName.setError("Please Enter Name");
                        binding.edOrderFullName.requestFocus();
                    } else if (binding.edOrderPhone.getText().length() != 10) {
                        binding.edOrderPhone.setError("Enter Valid Phone Number");
                        binding.edOrderPhone.requestFocus();
                    } else {
                        ConfirmOrder confirmOrder = new ConfirmOrder(Image, TotlePrice, Count, binding.edOrderFullName.getText().toString(),
                                binding.edOrderPhone.getText().toString(), Name);
                        boolean isInsert = dbHelper.ConfirmOrderInsert(confirmOrder);
                        if (isInsert) {
                            Toast.makeText(getContext(), "Order Add To Cart", Toast.LENGTH_SHORT).show();
                            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frMainContainer, new AddCartFragment());
                            fragmentTransaction.commit();
                        } else {
                            Toast.makeText(getContext(), "failed To Cart", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        } else if (UpdateType == 2) {
            int id = spf.getInt("id", 0);
            Cursor cursor = dbHelper.getOrderById(id);
            SharedPreferences.Editor editor = spf.edit();
            editor.remove("Update");
            editor.apply();

            binding.igOrderFood.setImageResource(cursor.getInt(4));
            binding.txOrderFoodName.setText(cursor.getString(3));
            binding.txOrderFoodRating.setVisibility(View.GONE);
            int TotalPrice = cursor.getInt(6);
            Quantity = cursor.getInt(5);
            int SingleFoodPrice = TotalPrice / Quantity;
            binding.txCount.setText("" + Quantity);
            binding.txOrderFoodPrice.setText("₹ " + SingleFoodPrice);
            binding.txTotalFoodPrice.setText("₹ " + TotalPrice);
            binding.edOrderFullName.setText(cursor.getString(1));
            binding.edOrderPhone.setText(cursor.getString(2));
            binding.btnOrderAddToCart.setText("Update Order");

            binding.btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Quantity >= 1) {
                        Quantity++;
                        binding.txCount.setText("" + Quantity);

                        UpdateTotalPrice = SingleFoodPrice * Quantity;

                    } else {
                        Quantity = 1;
                        binding.txCount.setText("" + Quantity);
                        UpdateTotalPrice = SingleFoodPrice;
                    }
                    binding.txTotalFoodPrice.setText("₹ " + UpdateTotalPrice);
                }
            });
            binding.btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Quantity > 1) {
                        Quantity--;
                        binding.txCount.setText("" + Quantity);
                        UpdateTotalPrice = SingleFoodPrice * Quantity;
                    } else {
                        Quantity = 1;
                        binding.txCount.setText("" + Quantity);
                        UpdateTotalPrice = SingleFoodPrice;
                    }
                    binding.txTotalFoodPrice.setText("₹ " + UpdateTotalPrice);
                }
            });

            binding.btnOrderAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (UpdateTotalPrice == 0) {
                        UpdateTotalPrice = SingleFoodPrice;
                    }
                    if (binding.edOrderFullName.getText().toString().isEmpty()) {
                        binding.edOrderFullName.setError("Please Enter Name");
                        binding.edOrderFullName.requestFocus();
                    } else if (binding.edOrderPhone.getText().length() != 10) {
                        binding.edOrderPhone.setError("Enter Valid Phone Number");
                        binding.edOrderPhone.requestFocus();
                    } else {
                        ConfirmOrder confirmOrder = new ConfirmOrder(id, cursor.getInt(4), UpdateTotalPrice, Quantity,
                                binding.edOrderFullName.getText().toString(), binding.edOrderPhone.getText().toString(),
                                cursor.getString(3));
                        boolean isUpdate = dbHelper.UpdateConfirmOrder(confirmOrder);
                        if (isUpdate) {
                            Toast.makeText(getContext(), "Update Order", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Failed Order", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

        return binding.getRoot();
    }
}
