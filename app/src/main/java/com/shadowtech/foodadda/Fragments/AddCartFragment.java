package com.shadowtech.foodadda.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shadowtech.foodadda.Adapter.AddToCartAdapter;
import com.shadowtech.foodadda.Api.ApiUtilities;
import com.shadowtech.foodadda.Model.AddToCart;
import com.shadowtech.foodadda.R;

import com.shadowtech.foodadda.databinding.FragmentAddCartBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddCartFragment extends Fragment {

    private List<AddToCart> addToCarts;
    private int TotalBillPayment = 0;
    private SharedPreferences spf;
    private int UserId;
    private FragmentAddCartBinding binding;

    public AddCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddCartBinding.inflate(getLayoutInflater());

        // Load The Data
        LoadData();

        // Back Button
        binding.igBackAddCartToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frMainContainer, new DashboardFragment());
                fragmentTransaction.commit();
            }
        });

        return binding.getRoot();
    }

    // Load The Data
    private void LoadData() {
        spf = requireContext().getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        UserId = spf.getInt("UserId", 0);

        // Get Cart Orders By UserId
        ApiUtilities.apiInterface().getCartOrders(UserId).enqueue(new Callback<List<AddToCart>>() {
            @Override
            public void onResponse(Call<List<AddToCart>> call, Response<List<AddToCart>> response) {
                addToCarts = new ArrayList<>();
                addToCarts = response.body();
                if (addToCarts.get(0).getMessage() == null) {
                    AddToCartAdapter adapter = new AddToCartAdapter(getContext(), addToCarts);
                    binding.rvAddCart.setAdapter(adapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    binding.rvAddCart.setLayoutManager(linearLayoutManager);

                    for (int i = 0; i < addToCarts.size(); i++) {
                        TotalBillPayment = TotalBillPayment + addToCarts.get(i).getTotal_price();
                    }
                    binding.txTotalItemPrice.setText("₹ " + TotalBillPayment);
                    binding.btnCartBillPayment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (TotalBillPayment == 0) {
                                Toast.makeText(getContext(), "Please Order Items", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getContext(), "₹ " + TotalBillPayment + " Payment SuccessFully \n Thank You !!!!", Toast.LENGTH_SHORT).show();
                                SharedPreferences preferences = requireContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putInt("UserId", UserId);
                                editor.apply();
                                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.frMainContainer, new animationFragment());
                                fragmentTransaction.addToBackStack(null).commit();
                            }
                        }
                    });
                } else {
                    Toast.makeText(requireContext(), "Please Order Items", Toast.LENGTH_SHORT).show();
                    binding.txTotalItemPrice.setText("0");
                    binding.btnCartBillPayment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), "Please Order Items", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<List<AddToCart>> call, Throwable t) {
                Toast.makeText(requireContext(), "Something went wrong!!" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
