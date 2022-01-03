package com.shadowtech.foodadda.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.shadowtech.foodadda.Db.DbHelper;
import com.shadowtech.foodadda.Model.ConfirmOrder;
import com.shadowtech.foodadda.R;
import com.shadowtech.foodadda.databinding.FragmentBottomSheetAddCartBinding;

import java.util.Date;

public class BottomSheetAddCartFragment extends BottomSheetDialogFragment {

    DbHelper dbHelper;

    public BottomSheetAddCartFragment() {

    }

    FragmentBottomSheetAddCartBinding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetAddCartBinding.inflate(getLayoutInflater());
//        dbHelper = new DbHelper(getContext());
//        SharedPreferences spf = requireContext().getSharedPreferences("AllMenuItemsDetail" , Context.MODE_PRIVATE);
//        int Image = spf.getInt("Image", 0);
//        String Name = spf.getString("Name" , null);
//        int Quantity = spf.getInt("Quantity" , 0);
//        String Delivery = spf.getString("Delivery" , null);
//        int Price = spf.getInt("TotalPrice" , 0);
//
//        binding.bsPrice.setText("₹ "+Price);
//        binding.bsDelivery.setText(Delivery);
//        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(binding.bsFullName.getText().toString().isEmpty())
//                {
//                    binding.bsFullName.setError("Please Fill The Full Name");
//                    binding.bsFullName.requestFocus();
//                }
//                else if(binding.bsPhone.getText().length() != 10)
//                {
//                    binding.bsPhone.setError("Enter Valid Phone Number");
//                    binding.bsPhone.requestFocus();
//                }
//                else if(binding.bsAddress.getText().toString().isEmpty())
//                {
//                    binding.bsAddress.setError("Please Fill Correct Address");
//                    binding.bsAddress.requestFocus();
//                }
//                else {
//                    ConfirmOrder confirmOrder = new ConfirmOrder(Image, Price,Quantity, binding.bsFullName.getText().toString(), binding.bsPhone.getText().toString(),
//                            binding.bsAddress.getText().toString(),Name);
//                     boolean insert = dbHelper.ConfirmOrder(confirmOrder);
//                     if(insert)
//                     {
//                         Toast.makeText(getContext(), "Add To Cart Order ", Toast.LENGTH_SHORT).show();
//                         FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
//                         fragmentTransaction.remove(BottomSheetAddCartFragment.this);
//                         fragmentTransaction.replace(R.id.frMainContainer , new DashboardFragment());
//                         fragmentTransaction.commit();
//                     }
//                     else {
//                         Toast.makeText(getContext(), "Failed Order", Toast.LENGTH_SHORT).show();
//                     }
//
//
//
//                }
//            }
//        });


        return binding.getRoot();
    }
}