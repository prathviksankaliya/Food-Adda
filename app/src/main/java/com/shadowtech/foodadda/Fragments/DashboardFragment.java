package com.shadowtech.foodadda.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadowtech.foodadda.Adapter.AllMenuItemsAdapter;
import com.shadowtech.foodadda.Adapter.PopularItemsRecyclerAdapter;
import com.shadowtech.foodadda.Db.DbHelper;
import com.shadowtech.foodadda.Model.AddToCart;
import com.shadowtech.foodadda.Model.AllMenuItems;
import com.shadowtech.foodadda.Model.PopularItems;
import com.shadowtech.foodadda.R;
import com.shadowtech.foodadda.databinding.FragmentDashboardBinding;

import java.util.ArrayList;


public class DashboardFragment extends Fragment {

    public DashboardFragment() {
        // Required empty public constructor
    }

    FragmentDashboardBinding binding;
    ArrayList<PopularItems> popularItems;
    ArrayList<AllMenuItems> allMenuItems;
    DbHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(getLayoutInflater());
        dbHelper = new DbHelper(getContext());
        ArrayList<AddToCart> addToCarts = dbHelper.AddToCartShowOrders();

        if (addToCarts.isEmpty()) {
            binding.igCartHome.setImageResource(R.drawable.bag);
        } else {
            binding.igCartHome.setImageResource(R.drawable.fillbag);
        }
        popularItems = new ArrayList<>();
        popularItems.add(new PopularItems("Pasta", R.drawable.pasta64));
        popularItems.add(new PopularItems("Pizza", R.drawable.pizzamenu));
        popularItems.add(new PopularItems("Samosa", R.drawable.samosa));
        popularItems.add(new PopularItems("Burger", R.drawable.burger));
        popularItems.add(new PopularItems("Sandwich", R.drawable.sandwich));
        popularItems.add(new PopularItems("Kachori", R.drawable.kachori));
        popularItems.add(new PopularItems("Dosa", R.drawable.dosa));
        popularItems.add(new PopularItems("Vadapav", R.drawable.vadapav));
        popularItems.add(new PopularItems("French Fry", R.drawable.ff));

        PopularItemsRecyclerAdapter adapter = new PopularItemsRecyclerAdapter(getContext(), popularItems);
        binding.rvpopularItems.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.rvpopularItems.setLayoutManager(linearLayoutManager);

        allMenuItems = new ArrayList<>();
        allMenuItems.add(new AllMenuItems(R.drawable.pasta64, "American Pasta", "Special", "4.8", "120", "Paid Delivery"));
        allMenuItems.add(new AllMenuItems(R.drawable.pizzamenu, "Margarita Pizza", "Regular", "4.0", "299", "Free Delivery"));
        allMenuItems.add(new AllMenuItems(R.drawable.samosa, "Maha Samosa", "Special", "3.7", "80", "Paid Delivery"));
        allMenuItems.add(new AllMenuItems(R.drawable.burger, "Cheese Burger", "Regular", "4.1", "60", "Paid Delivery"));
        allMenuItems.add(new AllMenuItems(R.drawable.sandwich, "Paneer Sandwich", "Regular", "3.9", "140", "Paid Delivery"));
        allMenuItems.add(new AllMenuItems(R.drawable.kachori, "Sweet Kachori", "Special", "4.3", "115", "Paid Delivery"));
        allMenuItems.add(new AllMenuItems(R.drawable.dosa, "Masala Dosa", "Special", "4.8", "199", "Free Delivery"));
        allMenuItems.add(new AllMenuItems(R.drawable.ff, "French Fry", "Regular", "4.4", "99", "Paid Delivery"));

        allMenuItems.add(new AllMenuItems(R.drawable.pasta64, "Cheese Pasta", "Special", "4.8", "260", "Free Delivery"));
        allMenuItems.add(new AllMenuItems(R.drawable.pizzamenu, "crusts Pizza", "Special", "4.0", "399", "Free Delivery"));
        allMenuItems.add(new AllMenuItems(R.drawable.samosa, "Spicy Samosa", "Regular", "4.9", "140", "Paid Delivery"));
        allMenuItems.add(new AllMenuItems(R.drawable.burger, "Maha Burger", "Regular", "4.1", "99", "Paid Delivery"));
        allMenuItems.add(new AllMenuItems(R.drawable.sandwich, "Masala Sandwich", "Special", "3.0", "170", "Free Delivery"));
        allMenuItems.add(new AllMenuItems(R.drawable.kachori, "Regular Kachori", "Regular", "4.4", "130", "Paid Delivery"));
        allMenuItems.add(new AllMenuItems(R.drawable.dosa, "Maisuri Dosa", "Special", "3.5", "199", "Free Delivery"));
        allMenuItems.add(new AllMenuItems(R.drawable.ff, "Masala Fry", "Special", "4.5", "130", "Home Delivery"));

        AllMenuItemsAdapter allMenuItemsAdapter = new AllMenuItemsAdapter(getContext(), allMenuItems);
        binding.rvAllMenuItems.setAdapter(allMenuItemsAdapter);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        binding.rvAllMenuItems.setLayoutManager(linearLayoutManager2);

        binding.igCartHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frMainContainer, new AddCartFragment());
                fragmentTransaction.commit();
            }
        });


        return binding.getRoot();
    }

}