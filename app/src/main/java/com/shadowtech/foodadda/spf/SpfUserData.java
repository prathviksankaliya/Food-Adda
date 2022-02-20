package com.shadowtech.foodadda.spf;

import android.content.Context;
import android.content.SharedPreferences;

public class SpfUserData {
    Context context;

    public SpfUserData(Context context) {
        this.context = context;
    }

    public void setSpfData(String Image, String Name, String Special, String Rating, String Price, String Delivery, int Insert, int quntity,int id, int update)
    {
        SharedPreferences spf = context.getSharedPreferences("AllMenuItemsDetail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("Image", Image);
        editor.putString("Name", Name);
        editor.putString("Special", Special);
        editor.putString("Rating", Rating);
        editor.putString("Price", Price);
        editor.putInt("Quntity", quntity);
        editor.putInt("id", id);
        editor.putInt("Update", update);
        editor.putString("Delivery", Delivery);
        editor.putInt("Insert", Insert);
        editor.apply();
    }

    public SharedPreferences getSpfData()
    {

        SharedPreferences spf = context.getSharedPreferences("AllMenuItemsDetail", Context.MODE_PRIVATE);

        return spf;
    }

}
