package com.shadowtech.foodadda.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shadowtech.foodadda.Adapter.AllMenuItemsAdapter;
import com.shadowtech.foodadda.Adapter.PopularItemsRecyclerAdapter;
import com.shadowtech.foodadda.Api.ApiUtilities;
import com.shadowtech.foodadda.Model.AllMenuItems;
import com.shadowtech.foodadda.Model.PopularItems;
import com.shadowtech.foodadda.R;
import com.shadowtech.foodadda.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment {

    public DashboardFragment() {
        // Required empty public constructor
    }

    private FragmentDashboardBinding binding;
    private List<AllMenuItems> allMenuItems;
    private List<PopularItems> list;
    private String searchtext;
    private AllMenuItemsAdapter allMenuItemsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(getLayoutInflater());
        // Search The MenuItem By Name
        binding.edSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.edSearch.getText().toString().isEmpty()) {
                    searchtext = binding.edSearch.getText().toString();
                    SearchData(searchtext);
                } else {
                    LoadData();
                }

            }
        });

        // Load The Data
        LoadData();

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

    // Search The MenuItem By Name
    private void SearchData(String searchtext) {
        ApiUtilities.apiInterface().SearchByName(searchtext).enqueue(new Callback<List<AllMenuItems>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<AllMenuItems>> call, Response<List<AllMenuItems>> response) {
                assert response.body() != null;
                allMenuItems.clear();
                allMenuItems.addAll(response.body());
                if (allMenuItems != null) {

                    if ( allMenuItems.get(0).getStatus() == 1) {
                        allMenuItemsAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(requireContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<AllMenuItems>> call, Throwable t) {
                Toast.makeText(requireContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Load The Categories
    private void LoadData() {
        ApiUtilities.apiInterface().getPopularCat().enqueue(new Callback<List<PopularItems>>() {
            @Override
            public void onResponse(Call<List<PopularItems>> call, Response<List<PopularItems>> response) {
                list = new ArrayList<>();
                list = response.body();

                PopularItemsRecyclerAdapter adapter = new PopularItemsRecyclerAdapter(getContext(), list);
                binding.rvpopularItems.setAdapter(adapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                binding.rvpopularItems.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<List<PopularItems>> call, Throwable t) {
                Toast.makeText(requireContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Load The Menu Items
        ApiUtilities.apiInterface().getPopularMenuItems().enqueue(new Callback<List<AllMenuItems>>() {
            @Override
            public void onResponse(Call<List<AllMenuItems>> call, Response<List<AllMenuItems>> response) {
                allMenuItems = new ArrayList<>();
                allMenuItems = response.body();

                allMenuItemsAdapter = new AllMenuItemsAdapter(getContext(), allMenuItems);
                binding.rvAllMenuItems.setAdapter(allMenuItemsAdapter);
                LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
                binding.rvAllMenuItems.setLayoutManager(linearLayoutManager2);

            }

            @Override
            public void onFailure(Call<List<AllMenuItems>> call, Throwable t) {
                Toast.makeText(requireContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}