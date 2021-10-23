package com.example.coctails.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.coctails.JsonPlaceHolderApi
import com.example.coctails.R
import com.example.coctails.databinding.ActivityMainBinding
import com.example.coctails.utils.DrinksResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CocktailsFragment : Fragment() {

    private var jsonPlaceHolderApi: JsonPlaceHolderApi? = null
    var mDrinksResult : DrinksResult? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cocktails, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //it was step 1 here
        val retrofit = Retrofit.Builder()
            .baseUrl("https://thecocktaildb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        //it was step 2 here
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        getDrinks()
    }

    private fun getDrinks() {
        val call: Call<DrinksResult?> = jsonPlaceHolderApi!!.getDrinks()

        call.enqueue(object : Callback<DrinksResult?> {
            override fun onResponse(
                call: Call<DrinksResult?>,
                response: Response<DrinksResult?>
            ) {
                if (!response.isSuccessful) {
                    //textViewResult.setText("Code: " + response.code())
                    return
                }
                mDrinksResult = response.body()
                Log.d("-------------", "-------------" + mDrinksResult?.drinks.toString())

            }

            override fun onFailure(call: Call<DrinksResult?>, t: Throwable) {
                Log.d("-------------", "++++++++++++++++++++++",t)
            }
        })
    }
}