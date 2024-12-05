package com.example.coctails.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.coctails.JsonPlaceHolderApi
import com.example.coctails.databinding.FragmentDetailsBinding
import com.example.coctails.utils.DetailsResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsFragment(drinkId: String?) : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    val mBinding get() = _binding!!
    private var textView: TextView? = null
    private var jsonPlaceHolderApi: JsonPlaceHolderApi? = null
    var mDetailsResult : DetailsResult? = null
    val mDrinkId : String? = drinkId


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://thecocktaildb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        Log.d("hell", mDrinkId!!)
        getDetails(mDrinkId!!)
        return mBinding.root
    }


    private fun getDetails(id: String) {
        val call: Call<DetailsResult> = jsonPlaceHolderApi!!.getDetails(id)
        Log.d("getDetails", id)
        call.enqueue(object : Callback<DetailsResult?> {
            override fun onResponse(
                call: Call<DetailsResult?>,
                response: Response<DetailsResult?>
            ) {
                if (!response.isSuccessful) {
                    return
                }
                //need to append in textview
                //mBinding.detailsText.text = response.body().toString()
                mDetailsResult = response.body()
                Picasso.get().load(mDetailsResult!!.drinks!![0].strDrinkThumb).into(mBinding.detailsPic)
                val info = "Name: " + mDetailsResult!!.drinks!![0].strDrink + "\n\n" +
                        "Category: " + mDetailsResult!!.drinks!![0].strCategory + "\n\n" +
                        "Ingredient1: " + mDetailsResult!!.drinks!![0].strIngredient1 + "\n\n" +
                        "Ingredient2: " + mDetailsResult!!.drinks!![0].strIngredient2 + "\n\n" +
                        "Ingredient3: " + mDetailsResult!!.drinks!![0].strIngredient3 + "\n\n" +
                        "Instructions: " + mDetailsResult!!.drinks!![0].strInstructions + "\n\n" +
                        "Glass: " + mDetailsResult!!.drinks!![0].strGlass + "\n\n" +
                        "Tags: " + mDetailsResult!!.drinks!![0].strTags + "\n\n"
                mBinding.detailsText.text = info
            }

            override fun onFailure(call: Call<DetailsResult?>, t: Throwable) {

            }
        })
    }
}