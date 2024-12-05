package com.example.coctails.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.coctails.JsonPlaceHolderApi
import com.example.coctails.R
import com.example.coctails.databinding.FragmentCocktailsBinding
import com.example.coctails.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CocktailsFragment : Fragment() {

    private var _binding: FragmentCocktailsBinding? = null
    val mBinding get() = _binding!!
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: CocktailsAdapter
    private var jsonPlaceHolderApi: JsonPlaceHolderApi? = null
    var mCocktailsResult : CocktailsResult? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCocktailsBinding.inflate(layoutInflater, container, false)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://thecocktaildb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

            mBinding.swipe.setOnRefreshListener {
                refreshAction()                    // refresh your list contents somehow
                mBinding.swipe.isRefreshing = false
            }
        //it was step 2 here
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        getCocktails()
        // Inflate the layout for this fragment
        return mBinding.root
    }

    private fun refreshAction() {
        getCocktails()
    }

    private fun getCocktails() {
        val call: Call<CocktailsResult?> = jsonPlaceHolderApi!!.getCocktails()

        call.enqueue(object : Callback<CocktailsResult?> {
            override fun onResponse(
                call: Call<CocktailsResult?>,
                response: Response<CocktailsResult?>
            ) {
                if (!response.isSuccessful) {
                    //textViewResult.setText("Code: " + response.code())
                    return
                }
                mCocktailsResult = response.body()
                mAdapter.setList(mCocktailsResult?.drinks!!)
                mAdapter.notifyDataSetChanged()
                Log.d("-------------", "-------------" + mCocktailsResult?.drinks.toString())

            }
            override fun onFailure(call: Call<CocktailsResult?>, t: Throwable) {
                Log.d("-------------", "++++++++++++++++++++++",t)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mAdapter = CocktailsAdapter(object : CocktailsAdapter.ClickListener {
            override fun onItemClick(drinkId: String?, v: View?) {
                Log.d("hell", drinkId!!)
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, DetailsFragment(drinkId))
                    .commit()
            }
        })
        mRecyclerView = mBinding.recyclerView
        mRecyclerView.adapter = mAdapter
    }

    companion object {
        fun click(drink: Drinks){
            val bundle = Bundle()
            bundle.putSerializable("drink", drink)
        }
    }
}