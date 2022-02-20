package com.shadowtech.foodadda.Api;

import com.shadowtech.foodadda.Model.AddToCart;
import com.shadowtech.foodadda.Model.AllMenuItems;
import com.shadowtech.foodadda.Model.PopularItems;
import com.shadowtech.foodadda.Model.Responce;
import com.shadowtech.foodadda.Model.UserDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {


    @POST("read_category.php")
    Call<List<PopularItems>> getPopularCat();

    @POST("read_menuitem.php")
    Call<List<AllMenuItems>> getPopularMenuItems();

    @FormUrlEncoded
    @POST("add_cart.php")
    Call<Responce> ConfirmOrder(
            @Field("user_id") int user_id,
            @Field("quantity") int quantity, @Field("total_price") String total_price,
            @Field("food_name") String food_name, @Field("food_img") String food_img
    );
    @FormUrlEncoded
    @POST("create_user.php")
    Call<Responce> UserDetails(
            @Field("name") String name, @Field("email") String email,
            @Field("phone") String phone, @Field("address") String address,
            @Field("status") int status
    );

    @FormUrlEncoded
    @POST("read_user.php")
    Call<UserDetails> ReadUser(
            @Field("email") String email,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("read_cart.php")
    Call<List<AddToCart>> getCartOrders(
            @Field("user_id") int userid
    );

    @FormUrlEncoded
    @POST("delete_cart.php")
    Call<Responce> DeleteCartOrders(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("delete_cartbyid.php")
    Call<Responce> DeleteCartOrdersbyId(
            @Field("user_id") int user_id
    );

    @FormUrlEncoded
    @POST("search_item.php")
    Call<List<AllMenuItems>> SearchByName(
            @Field("name") String name
    );


    @FormUrlEncoded
    @POST("update_cart.php")
    Call<Responce> UpdateOrder(
            @Field("id") int id,
            @Field("quantity") int quantity, @Field("total_price") String total_price,
            @Field("food_name") String food_name, @Field("food_img") String food_img
    );
}
