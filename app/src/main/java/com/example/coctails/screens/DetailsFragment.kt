package com.example.coctails.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.coctails.JsonPlaceHolderApi
import com.example.coctails.R
import com.example.coctails.databinding.FragmentDetailsBinding
import com.example.coctails.databinding.FragmentDrinksBinding
import com.example.coctails.utils.Details
import com.example.coctails.utils.DetailsResult
import com.example.coctails.utils.DrinksResult
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    val mBinding get() = _binding!!
    private var textView: TextView? = null
    private var jsonPlaceHolderApi: JsonPlaceHolderApi? = null
    var mDetailsResult : DetailsResult? = null

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
        getDetails("15300")
        return mBinding.root
    }


    private fun getDetails(id: String) {
        val call: Call<DetailsResult> = jsonPlaceHolderApi!!.getDetails(id)

        call.enqueue(object : Callback<DetailsResult?> {
            override fun onResponse(
                call: Call<DetailsResult?>,
                response: Response<DetailsResult?>
            ) {
                if (!response.isSuccessful) {
                    return
                }
                //need to append in textview
                textView!!.text = response.body().toString()

            }

            override fun onFailure(call: Call<DetailsResult?>, t: Throwable) {
                Log.d("-------------", "++++++++++++++++++++++",t)
            }
        })
    }
}