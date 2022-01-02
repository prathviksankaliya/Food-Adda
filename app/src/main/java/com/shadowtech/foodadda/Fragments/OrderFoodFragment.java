package com.shadowtech.foodadda.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.shadowtech.foodadda.R;
import com.shadowtech.foodadda.databinding.FragmentBottomSheetAddCartBinding;
import com.shadowtech.foodadda.databinding.FragmentOrderFoodBinding;


public class OrderFoodFragment extends Fragment {



    public OrderFoodFragment() {
        // Required empty public constructor
    }
    FragmentOrderFoodBinding binding;
    int TotlePrice,Count = 1 ;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderFoodBinding.inflate(getLayoutInflater());

        SharedPreferences spf = requireContext().getSharedPreferences("AllMenuItemsDetail" , Context.MODE_PRIVATE);
        int Image = spf.getInt("Image", 0);
        String Name = spf.getString("Name" , null);
        String Special = spf.getString("Special" , null);
        String Rating = spf.getString("Rating" , null);
        String Delivery = spf.getString("Delivery" , null);
        String Price = spf.getString("Price" , null);
        SharedPreferences.Editor editor = spf.edit();
        editor.clear();
        editor.apply();
        binding.igOrderFood.setImageResource(Image);
        binding.txOrderFoodName.setText(Name);
        binding.txOrderFoodRating.setText(Rating);
        binding.txOrderFoodPrice.setText("₹ "+Price);
        binding.txTotalFoodPrice.setText("₹ "+Price);
        int FoodPrice = Integer.parseInt(Price);

        binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Count >= 1)
                {
                    Count++;
                    binding.txCount.setText(""+Count);

                    TotlePrice = FoodPrice * Count;

                }
                else {
                    Count = 1;
                    binding.txCount.setText(""+Count);
                     TotlePrice = FoodPrice;
                }
                binding.txTotalFoodPrice.setText("₹ "+TotlePrice);
            }
        });
        binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Count > 1)
                {
                    Count--;
                    binding.txCount.setText(""+Count);
                    TotlePrice = FoodPrice * Count;
                }
                else {
                    Count = 1;
                    binding.txCount.setText(""+Count);
                    TotlePrice = FoodPrice;
                }
                binding.txTotalFoodPrice.setText("₹ "+TotlePrice);
            }
        });



        binding.igBackDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frMainContainer , new DashboardFragment());
                fragmentTransaction.commit();
            }
        });

        binding.igAddCartOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetAddCartFragment bottomSheetAddCartFragment = new BottomSheetAddCartFragment();
                bottomSheetAddCartFragment.show(getParentFragmentManager() , bottomSheetAddCartFragment.getTag());
            }
        });
        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TotlePrice < FoodPrice)
                {
                    Toast.makeText(getContext(), "Please Count Item", Toast.LENGTH_SHORT).show();
                }
                else {
                   String CartItem =  binding.txCount.getText().toString();
                   binding.igAddCartOrder.setImageResource(R.drawable.fillbag);
                   binding.txAddCartItem.setVisibility(View.VISIBLE);
                   binding.txAddCartItem.setText(CartItem);
                }
            }
        });

        return binding.getRoot();
    }
}