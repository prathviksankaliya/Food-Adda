package com.shadowtech.foodadda.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    //    Base URL From Hosting
//    private static final String BASE_URL = "http://192.168.0.101/foodadda_api/api/";
    private static final String BASE_URL = "https://foodaddaandroidapp.000webhostapp.com/api/";
    //    Category Image URL
//    public static final String CatImageUrl = "http://192.168.0.101/foodadda_api/admin/images/category/";
    public static final String CatImageUrl = "https://foodaddaandroidapp.000webhostapp.com/ADMIN/images/category/";
    //    Menu Item Image URL
//    public static final String MenuItemImageUrl = "http://192.168.0.101/foodadda_api/admin/images/menuitem/";
    public static final String MenuItemImageUrl = "https://foodaddaandroidapp.000webhostapp.com/ADMIN/images/menuitem/";

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
