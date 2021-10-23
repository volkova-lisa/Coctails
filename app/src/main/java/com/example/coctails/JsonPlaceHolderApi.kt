package com.example.coctails

import com.example.coctails.utils.CocktailsResult
import com.example.coctails.utils.DrinksResult
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {
    @GET("/api/json/v1/1/filter.php?c=Ordinary_Drink")
    fun getDrinks(): Call<DrinksResult?>

    @GET("/api/json/v1/1/filter.php?c=Cocktail")
    fun getCocktails(): Call<CocktailsResult?>
}