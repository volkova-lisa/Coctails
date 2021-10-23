package com.example.coctails.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coctails.JsonPlaceHolderApi
import com.example.coctails.databinding.FragmentDrinksBinding
import com.example.coctails.utils.Drinks
import com.example.coctails.utils.DrinksAdapter
import com.example.coctails.utils.DrinksResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.squareup.picasso.Picasso

import android.R




class DrinksFragment : Fragment() {

    private var _binding: FragmentDrinksBinding? = null
    val mBinding get() = _binding!!
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: DrinksAdapter
    private var jsonPlaceHolderApi: JsonPlaceHolderApi? = null
    var mDrinksResult : DrinksResult? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDrinksBinding.inflate(layoutInflater, container, false)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://thecocktaildb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        //it was step 2 here
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        getDrinks()
        // Inflate the layout for this fragment
        return mBinding.root
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
                mAdapter.setList(mDrinksResult?.drinks!!)
                mAdapter.notifyDataSetChanged()
                Log.d("-------------", "-------------" + mDrinksResult?.drinks.toString())

            }
            override fun onFailure(call: Call<DrinksResult?>, t: Throwable) {
                Log.d("-------------", "++++++++++++++++++++++",t)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mAdapter = DrinksAdapter()
        mRecyclerView = mBinding.recyclerView
        mRecyclerView.adapter = mAdapter
    }

//    override fun onDrinkClick(position: Int) {
//        val currPos = mDrinksResult!!.drinks!![position]
//        val transaction = requireActivity().supportFragmentManager.beginTransaction()
//        transaction.replace(com.example.coctails.R.id.nav_host_fragment, DetailsFragment())
//        transaction.commit()
//    }
}