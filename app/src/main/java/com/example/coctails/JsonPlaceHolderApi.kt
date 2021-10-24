package com.example.coctails

import com.example.coctails.utils.*
import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceHolderApi {
    @GET("/api/json/v1/1/filter.php?c=Ordinary_Drink")
    fun getDrinks(): Call<DrinksResult?>

    @GET("/api/json/v1/1/filter.php?c=Cocktail")
    fun getCocktails(): Call<CocktailsResult?>

    @GET("api/json/v1/1/lookup.php")
    fun getDetails(@Query("i")id:String) : Call<DetailsResult>

    @POST("api/json/v1/1/order")
    fun makeOrder(@Body orderInfo:Orders) : Call<Orders>
}