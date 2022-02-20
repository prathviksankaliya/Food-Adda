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

import com.bumptech.glide.Glide;
import com.shadowtech.foodadda.Api.ApiUtilities;
import com.shadowtech.foodadda.Model.Responce;
import com.shadowtech.foodadda.Model.UserDetails;
import com.shadowtech.foodadda.R;
import com.shadowtech.foodadda.databinding.FragmentOrderFoodBinding;
import com.shadowtech.foodadda.spf.SpfUserData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFoodFragment extends Fragment {

    public OrderFoodFragment() {
        // Required empty public constructor
    }

    private FragmentOrderFoodBinding binding;
    private int Count = 1;
    private int TotlePrice, FoodPrice, Quantity, UpdateTotalPrice, UserID;
    private String UserEmail, UserPhone;
    private SpfUserData spf;
    private SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderFoodBinding.inflate(getLayoutInflater());

        binding.igBackDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frMainContainer, new DashboardFragment());
                fragmentTransaction.commit();
            }
        });

        spf = new SpfUserData(requireContext());
        String Image = spf.getSpfData().getString("Image", null);
        String Name = spf.getSpfData().getString("Name", null);
        String Special = spf.getSpfData().getString("Special", null);
        String Rating = spf.getSpfData().getString("Rating", null);
        String Price = spf.getSpfData().getString("Price", null);
        Quantity = spf.getSpfData().getInt("Quntity", 0);
        String Delivery = spf.getSpfData().getString("Delivery", null);
        int InsertType = spf.getSpfData().getInt("Insert", 0);
        int UpdateType = spf.getSpfData().getInt("Update", 0);

        // Insert or Update Order Maintain To one Screen
        // Insert = 1 to Insert The Data
        if (InsertType == 1) {
            Glide.with(requireContext()).load(ApiUtilities.MenuItemImageUrl + Image).into(binding.igOrderFood);
            binding.txOrderFoodName.setText(Name);
            binding.txOrderFoodRating.setText(Rating);
            binding.txOrderFoodPrice.setText("₹ " + Price);
            binding.txTotalFoodPrice.setText("₹ " + Price);

            FoodPrice = Integer.parseInt(Price);

            // Quantity Plus
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

            // Quantity Minus
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

                    sharedPreferences = requireContext().getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
                    UserEmail = sharedPreferences.getString("UserEmail", null);
                    UserPhone = sharedPreferences.getString("UserPhone", null);
                    if (UserEmail == null && UserPhone == null) {
                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frMainContainer, new UserDetailsFragment());
                        fragmentTransaction.commit();
                    } else {
                        // Read User By Email , Phone
                        ApiUtilities.apiInterface().ReadUser(UserEmail, UserPhone).enqueue(new Callback<UserDetails>() {
                            @Override
                            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                                UserDetails model = response.body();
                                if (model != null) {
                                    if (model.getMessage() == null) {
                                        UserID = model.getId();
                                        sharedPreferences = requireContext().getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putInt("UserId", UserID);
                                        editor.apply();
                                        // Confirm Order With Data
                                        ApiUtilities.apiInterface().ConfirmOrder(UserID, Count, String.valueOf(TotlePrice), Name, Image).enqueue(new Callback<Responce>() {
                                            @Override
                                            public void onResponse(Call<Responce> call, Response<Responce> response) {
                                                Responce model = response.body();
                                                if (model != null) {
                                                    if (model.getMessage().equals("fail")) {
                                                        Toast.makeText(getContext(), "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Toast.makeText(getContext(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                                                        spf.setSpfData(Image, Name, Special, Rating, String.valueOf(TotlePrice), Delivery, 0, Count, 0, 0);
                                                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                                                        fragmentTransaction.replace(R.id.frMainContainer, new DashboardFragment());
                                                        fragmentTransaction.commit();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Responce> call, Throwable t) {
                                                Toast.makeText(getContext(), "Something Went Wrong order" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    } else {
                                        Toast.makeText(requireContext(), "Something went Wrong!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<UserDetails> call, Throwable t) {
                                Toast.makeText(getContext(), "Something Went Wrong" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

            });

            // Update = 2 to Update The Data
        } else if (UpdateType == 2) {
            SharedPreferences pref = requireContext().getSharedPreferences("AllMenuItemsDetail", Context.MODE_PRIVATE);
            int id = pref.getInt("id", 0);

            SharedPreferences.Editor editor = pref.edit();
            editor.remove("Update");
            editor.apply();

            // Load Online Image In app
            Glide.with(requireContext()).load(ApiUtilities.MenuItemImageUrl + Image).into(binding.igOrderFood);
            binding.txOrderFoodName.setText(Name);
            binding.txOrderFoodRating.setText(Rating);

            binding.txOrderFoodRating.setVisibility(View.GONE);
            int TotalPrice = Integer.parseInt(Price);
            int SingleFoodPrice = TotalPrice / Quantity;
            binding.txCount.setText("" + Quantity);
            binding.txOrderFoodPrice.setText("₹ " + SingleFoodPrice);
            binding.txTotalFoodPrice.setText("₹ " + TotalPrice);
            binding.btnOrderAddToCart.setText("Update Order");
            // Quantity Plus
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

            // Quantity Minus
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

                    // Update The Record
                    ApiUtilities.apiInterface().UpdateOrder(id, Quantity, String.valueOf(UpdateTotalPrice), Name, Image).enqueue(new Callback<Responce>() {
                        @Override
                        public void onResponse(Call<Responce> call, Response<Responce> response) {
                            Responce model = response.body();
                            if (model != null) {
                                if (model.getMessage().equals("fail")) {
                                    Toast.makeText(getContext(), "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.frMainContainer, new AddCartFragment());
                                    fragmentTransaction.commit();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Responce> call, Throwable t) {
                            Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            });

        }
        return binding.getRoot();
    }
}
