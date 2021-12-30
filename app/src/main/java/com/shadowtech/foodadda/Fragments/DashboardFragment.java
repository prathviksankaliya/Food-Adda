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
import com.shadowtech.foodadda.Adapter.RecommenedItemAdapter;
import com.shadowtech.foodadda.Model.AllMenuItems;
import com.shadowtech.foodadda.Model.PopularItems;
import com.shadowtech.foodadda.Model.Recommeneditems;
import com.shadowtech.foodadda.R;
import com.shadowtech.foodadda.databinding.FragmentDashboardBinding;

import java.util.ArrayList;


public class DashboardFragment extends Fragment {

    public DashboardFragment() {
        // Required empty public constructor
    }
    FragmentDashboardBinding binding;
    ArrayList<PopularItems> popularItems;
    ArrayList<Recommeneditems> recommeneditems;
    ArrayList<AllMenuItems> allMenuItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(getLayoutInflater());
        popularItems = new ArrayList<>();
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        popularItems.add(new PopularItems("Pasta" , R.drawable.pasta64));
        PopularItemsRecyclerAdapter adapter = new PopularItemsRecyclerAdapter(getContext() , popularItems);
        binding.rvpopularItems.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext() , RecyclerView.HORIZONTAL , false);
        binding.rvpopularItems.setLayoutManager(linearLayoutManager);

        recommeneditems = new ArrayList<>();
        recommeneditems.add(new Recommeneditems(R.drawable.pizza , "American Pizza" , "4.5" , "₹ 340"));
        recommeneditems.add(new Recommeneditems(R.drawable.pizza , "American Pizza" , "4.5" , "₹ 340"));
        recommeneditems.add(new Recommeneditems(R.drawable.pizza , "American Pizza" , "4.5" , "₹ 340"));
        recommeneditems.add(new Recommeneditems(R.drawable.pizza , "American Pizza" , "4.5" , "₹ 340"));
        recommeneditems.add(new Recommeneditems(R.drawable.pizza , "American Pizza" , "4.5" , "₹ 340"));
        recommeneditems.add(new Recommeneditems(R.drawable.pizza , "American Pizza" , "4.5" , "₹ 340"));
        recommeneditems.add(new Recommeneditems(R.drawable.pizza , "American Pizza" , "4.5" , "₹ 340"));
        recommeneditems.add(new Recommeneditems(R.drawable.pizza , "American Pizza" , "4.5" , "₹ 340"));
        recommeneditems.add(new Recommeneditems(R.drawable.pizza , "American Pizza" , "4.5" , "₹ 340"));
        recommeneditems.add(new Recommeneditems(R.drawable.pizza , "American Pizza" , "4.5" , "₹ 340"));

        RecommenedItemAdapter recommenedItemAdapter = new RecommenedItemAdapter(getContext() , recommeneditems);
        binding.rvrecommenedItems.setAdapter(recommenedItemAdapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false);
        binding.rvrecommenedItems.setLayoutManager(linearLayoutManager1);

        allMenuItems = new ArrayList<>();
        allMenuItems.add(new AllMenuItems(R.drawable.pasta64 , "American Pasta" , "Special" , "4.5" , "₹ 340"));
        allMenuItems.add(new AllMenuItems(R.drawable.pasta64 , "American Pasta" , "Special" , "4.5" , "₹ 340"));
        allMenuItems.add(new AllMenuItems(R.drawable.pasta64 , "American Pasta" , "Special" , "4.5" , "₹ 340"));
        allMenuItems.add(new AllMenuItems(R.drawable.pasta64 , "American Pasta" , "Special" , "4.5" , "₹ 340"));
        allMenuItems.add(new AllMenuItems(R.drawable.pasta64 , "American Pasta" , "Special" , "4.5" , "₹ 340"));
        allMenuItems.add(new AllMenuItems(R.drawable.pasta64 , "American Pasta" , "Special" , "4.5" , "₹ 340"));
        allMenuItems.add(new AllMenuItems(R.drawable.pasta64 , "American Pasta" , "Special" , "4.5" , "₹ 340"));
        allMenuItems.add(new AllMenuItems(R.drawable.pasta64 , "American Pasta" , "Special" , "4.5" , "₹ 340"));
        allMenuItems.add(new AllMenuItems(R.drawable.pasta64 , "American Pasta" , "Special" , "4.5" , "₹ 340"));
        allMenuItems.add(new AllMenuItems(R.drawable.pasta64 , "American Pasta" , "Special" , "4.5" , "₹ 340"));
        allMenuItems.add(new AllMenuItems(R.drawable.pasta64 , "American Pasta" , "Special" , "4.5" , "₹ 340"));

        AllMenuItemsAdapter allMenuItemsAdapter = new AllMenuItemsAdapter(getContext() , allMenuItems);
        binding.rvAllMenuItems.setAdapter(allMenuItemsAdapter);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        binding.rvAllMenuItems.setLayoutManager(linearLayoutManager2);

        binding.igCartHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frMainContainer , new OrderFoodFragment());
                fragmentTransaction.commit();
            }
        });

        return binding.getRoot();
    }
}