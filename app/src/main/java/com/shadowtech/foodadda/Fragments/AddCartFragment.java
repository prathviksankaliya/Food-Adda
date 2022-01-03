package com.shadowtech.foodadda.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shadowtech.foodadda.Adapter.AddToCartAdapter;
import com.shadowtech.foodadda.Db.DbHelper;
import com.shadowtech.foodadda.Model.AddToCart;
import com.shadowtech.foodadda.R;

import com.shadowtech.foodadda.databinding.FragmentAddCartBinding;

import java.util.ArrayList;


public class AddCartFragment extends Fragment {

    DbHelper dbHelper;
    AddToCart addToCart;
    ArrayList<AddToCart> addToCarts;
    int TotalBillPayment = 0;

    public AddCartFragment() {
        // Required empty public constructor
    }

    FragmentAddCartBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddCartBinding.inflate(getLayoutInflater());
        dbHelper = new DbHelper(getContext());
        binding.igBackAddCartToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frMainContainer, new DashboardFragment());
                fragmentTransaction.commit();
            }
        });
        ArrayList<AddToCart> addToCarts = dbHelper.AddToCartShowOrders();
        if (addToCarts.isEmpty()) {
            Toast.makeText(getContext(), "Empty Order", Toast.LENGTH_SHORT).show();
            binding.txTotalItemPrice.setText("0");
            binding.btnCartBillPayment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Please Order Items", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            AddToCartAdapter adapter = new AddToCartAdapter(getContext(), addToCarts);
            binding.rvAddCart.setAdapter(adapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            binding.rvAddCart.setLayoutManager(linearLayoutManager);
            for (int i = 0; i < addToCarts.size(); i++) {
                TotalBillPayment = TotalBillPayment + addToCarts.get(i).getTotalPrice();
            }
            binding.txTotalItemPrice.setText("₹ " + TotalBillPayment);
            binding.btnCartBillPayment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TotalBillPayment == 0) {
                        Toast.makeText(getContext(), "Please Order Items", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getContext(), "₹ " + TotalBillPayment + " Payment SuccessFully \n Thank You !!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        return binding.getRoot();
    }

}
