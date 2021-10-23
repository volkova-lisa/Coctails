package com.example.coctails

import com.example.coctails.utils.CocktailsResult
import com.example.coctails.utils.Details
import com.example.coctails.utils.DetailsResult
import com.example.coctails.utils.DrinksResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPlaceHolderApi {
    @GET("/api/json/v1/1/filter.php?c=Ordinary_Drink")
    fun getDrinks(): Call<DrinksResult?>

    @GET("/api/json/v1/1/filter.php?c=Cocktail")
    fun getCocktails(): Call<CocktailsResult?>

    @GET("api/json/v1/1/lookup.php")
    fun getDetails(@Query("i")id:String) : Call<DetailsResult>
}