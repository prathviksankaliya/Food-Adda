package com.shadowtech.foodadda.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.shadowtech.foodadda.Api.ApiUtilities;
import com.shadowtech.foodadda.Model.Responce;
import com.shadowtech.foodadda.R;
import com.shadowtech.foodadda.databinding.FragmentAnimationBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class animationFragment extends Fragment {


    public animationFragment() {
        // Required empty public constructor
    }

    private FragmentAnimationBinding binding;
    private int Id;
    private static final int SPLASH_SCREEN = 3000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAnimationBinding.inflate(getLayoutInflater());

        // Load Animation
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = requireContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
                Id = preferences.getInt("UserId", 0);
                ApiUtilities.apiInterface().DeleteCartOrdersbyId(Id).enqueue(new Callback<Responce>() {
                    @Override
                    public void onResponse(Call<Responce> call, Response<Responce> response) {
                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frMainContainer, new DashboardFragment());
                        fragmentTransaction.commit();
                    }

                    @Override
                    public void onFailure(Call<Responce> call, Throwable t) {
                        Toast.makeText(requireContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, SPLASH_SCREEN);
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
}