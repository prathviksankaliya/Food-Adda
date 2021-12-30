package com.shadowtech.foodadda.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadowtech.foodadda.R;
import com.shadowtech.foodadda.databinding.FragmentAddCartBinding;


public class AddCartFragment extends Fragment {


    public AddCartFragment() {
        // Required empty public constructor
    }
        FragmentAddCartBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddCartBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }
}