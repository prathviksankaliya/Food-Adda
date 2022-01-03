package com.shadowtech.foodadda.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.print.PageRange;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.shadowtech.foodadda.Model.AddToCart;
import com.shadowtech.foodadda.Model.ConfirmOrder;
import com.shadowtech.foodadda.Params.Params;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    // create table tablename (id int primery key autoincrement,
//                          name text,
//                          phone text,
//                          address text,
//                          image int,
//                          foodname text,
//                          price int,
//                          time datetime default CURRENT_TIMESTAMP);
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTable = "CREATE TABLE " + Params.TABLE_NAME + "(" + Params.KEY_ID + " INTEGER PRIMARY KEY,"
                + Params.KEY_USERNAME + " TEXT,"
                + Params.KEY_USERPHONE + " TEXT,"
                + Params.KEY_FOODNAME + " TEXT,"
                + Params.KEY_IMAGE + " INTEGER,"
                + Params.KEY_Quntity + " INTEGER,"
                + Params.KEY_TOTALPRICE + " INTEGER)";

        db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Params.TABLE_NAME);
    }

    public boolean ConfirmOrderInsert(ConfirmOrder confirmOrder) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.KEY_USERNAME, confirmOrder.getUserName());
        contentValues.put(Params.KEY_USERPHONE, confirmOrder.getUserPhone());
        contentValues.put(Params.KEY_FOODNAME, confirmOrder.getFoodName());
        contentValues.put(Params.KEY_IMAGE, confirmOrder.getImage());
        contentValues.put(Params.KEY_Quntity, confirmOrder.getQuntity());
        contentValues.put(Params.KEY_TOTALPRICE, confirmOrder.getTotalPrice());

        long Rid = db.insert(Params.TABLE_NAME, null, contentValues);
        if (Rid <= 0) {
            return false;
        } else {
            return true;
        }

    }

//    public ArrayList<ConfirmOrder> ShowOrders()
//    {
//        ArrayList<ConfirmOrder> confirmOrders = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//        String getOrder = "SELECT * FROM "+Params.TABLE_NAME;
//        Cursor cursor = db.rawQuery(getOrder, null);
//
//        if(cursor.moveToFirst())
//        {
//            do {
//                ConfirmOrder confirmOrder = new ConfirmOrder();
//                confirmOrder.setId(cursor.getInt(0));
//                confirmOrder.setUserName(cursor.getString(1));
//                confirmOrder.setUserPhone(cursor.getString(2));
//                confirmOrder.setFoodName(cursor.getString(3));
//                confirmOrder.setImage(cursor.getInt(4));
//                confirmOrder.setQuntity(cursor.getInt(5));
//                confirmOrder.setTotalPrice(cursor.getInt(6));
//                confirmOrders.add(confirmOrder);
//            }
//            while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return confirmOrders;
//    }

    public ArrayList<AddToCart> AddToCartShowOrders() {
        ArrayList<AddToCart> addToCarts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String getOrder = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(getOrder, null);

        if (cursor.moveToFirst()) {
            do {
                AddToCart addToCart = new AddToCart();
                addToCart.setId(cursor.getInt(0));
                addToCart.setName(cursor.getString(3));
                addToCart.setImage(cursor.getInt(4));
                addToCart.setQuantity(cursor.getInt(5));
                addToCart.setTotalPrice(cursor.getInt(6));
                addToCarts.add(addToCart);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return addToCarts;
    }

    public Cursor getOrderById(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String Order = "SELECT * FROM " + Params.TABLE_NAME + " WHERE " + Params.KEY_ID + " = " + id;
        Cursor cursor = db.rawQuery(Order, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean UpdateConfirmOrder(ConfirmOrder confirmOrder) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.KEY_ID, confirmOrder.getId());
        contentValues.put(Params.KEY_USERNAME, confirmOrder.getUserName());
        contentValues.put(Params.KEY_USERPHONE, confirmOrder.getUserPhone());
        contentValues.put(Params.KEY_FOODNAME, confirmOrder.getFoodName());
        contentValues.put(Params.KEY_IMAGE, confirmOrder.getImage());
        contentValues.put(Params.KEY_Quntity, confirmOrder.getQuntity());
        contentValues.put(Params.KEY_TOTALPRICE, confirmOrder.getTotalPrice());

        long Row = db.update(Params.TABLE_NAME, contentValues, Params.KEY_ID + " = " + confirmOrder.getId(), null);
        if (Row <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public int DeleteOrder(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(Params.TABLE_NAME, Params.KEY_ID + " = " + id, null);
    }


}
