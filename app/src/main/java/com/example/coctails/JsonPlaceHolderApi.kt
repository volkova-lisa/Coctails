package com.example.coctails

import com.example.coctails.utils.Drinks
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

public interface JsonPlaceHolderApi {
    @GET("api/json/v1/{API_KEY}/filter.php?c=Ordinary_Drink")
    fun getComments(@Path("id") postId: Int): Call<MutableList<Drinks?>?>?
}