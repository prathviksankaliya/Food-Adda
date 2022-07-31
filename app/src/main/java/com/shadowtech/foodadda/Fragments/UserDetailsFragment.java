package com.shadowtech.foodadda.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.shadowtech.foodadda.Api.ApiUtilities;
import com.shadowtech.foodadda.Model.Responce;
import com.shadowtech.foodadda.R;
import com.shadowtech.foodadda.databinding.FragmentUserDetailsBinding;
import com.shadowtech.foodadda.spf.SpfUserData;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsFragment extends Fragment {


    public UserDetailsFragment() {
        // Required empty public constructor
    }

    private FragmentUserDetailsBinding binding;
    private String Name, Phone, Address, Email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailsBinding.inflate(getLayoutInflater());
        SpfUserData spf = new SpfUserData(requireContext());
        String Image = spf.getSpfData().getString("Image", null);
        String FoodName = spf.getSpfData().getString("Name", null);
        String Special = spf.getSpfData().getString("Special", null);
        String Rating = spf.getSpfData().getString("Rating", null);
        String Price = spf.getSpfData().getString("Price", null);
        String Delivery = spf.getSpfData().getString("Delivery", null);

        binding.igBackDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frMainContainer, new OrderFoodFragment());
                fragmentTransaction.commit();
            }
        });

        // Save The User Profile
        binding.btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edOrderFullName.getText().length() < 2) {
                    binding.edOrderFullName.setError("Name Must be 3 characters");
                    binding.edOrderFullName.requestFocus();
                } else if (!ValidEmail(binding.edUserEmail.getText().toString())) {
                    binding.edUserEmail.setError("Please Enter Email In Valid Format");
                    binding.edUserEmail.requestFocus();
                } else if (binding.edUserPhone.getText().toString().length() != 10) {
                    binding.edUserPhone.setError("PhoneNumber must be Minimum 10 Digits");
                    binding.edUserPhone.requestFocus();
                } else if (binding.edUserAddress.getText().length() < 7) {
                    binding.edUserAddress.setError("Address Must be 8 characters");
                    binding.edUserAddress.requestFocus();
                } else {
                    Name = binding.edOrderFullName.getText().toString();
                    Email = binding.edUserEmail.getText().toString();
                    Phone = binding.edUserPhone.getText().toString();
                    Address = binding.edUserAddress.getText().toString();
                    Phone = "+91" + Phone;

                    // Send User Profile To db
                    ApiUtilities.apiInterface().UserDetails(Name, Email, Phone, Address, 1)
                            .enqueue(new Callback<Responce>() {
                                @Override
                                public void onResponse(Call<Responce> call, Response<Responce> response) {
                                    Responce model = response.body();
                                    if (model != null) {
                                        if (model.getMessage().equals("fail")) {
                                            Toast.makeText(getContext(), "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
                                        } else {

                                            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("UserName", Name);
                                            editor.putString("UserPhone", Phone);
                                            editor.putString("UserEmail", Email);
                                            editor.putString("UserAddress", Address);
                                            editor.apply();
                                            Toast.makeText(getContext(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                                            spf.setSpfData(Image, FoodName, Special, Rating, Price, Delivery, 1, 0, 0, 0);
                                            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                                            fragmentTransaction.replace(R.id.frMainContainer, new OrderFoodFragment());
                                            fragmentTransaction.commit();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<Responce> call, Throwable t) {
                                    Toast.makeText(getContext(), "Something Went Wrong!!" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        return binding.getRoot();
    }

    private boolean ValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}