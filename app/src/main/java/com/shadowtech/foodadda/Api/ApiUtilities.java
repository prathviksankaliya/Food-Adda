package com.shadowtech.foodadda.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    private static final String BASE_URL = "http://192.168.0.105:80/foodadda_api/api/";
    public static final String CatImageUrl = "http://192.168.0.105:80/foodadda_api/admin/images/category/";
    public static final String MenuItemImageUrl = "http://192.168.0.105:80/foodadda_api/admin/images/menuitem/";

    public static Retrofit retrofit = null;

    public static ApiInterface apiInterface() {
        if (retrofit == null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
