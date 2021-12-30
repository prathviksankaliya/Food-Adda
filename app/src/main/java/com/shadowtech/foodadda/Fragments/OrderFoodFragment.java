package com.shadowtech.foodadda.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadowtech.foodadda.R;
import com.shadowtech.foodadda.databinding.FragmentOrderFoodBinding;


public class OrderFoodFragment extends Fragment {



    public OrderFoodFragment() {
        // Required empty public constructor
    }
    FragmentOrderFoodBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderFoodBinding.inflate(getLayoutInflater());
        binding.igBackDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frMainContainer , new DashboardFragment());
                fragmentTransaction.commit();
            }
        });

        return binding.getRoot();
    }
}