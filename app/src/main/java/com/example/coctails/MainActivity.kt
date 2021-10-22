package com.example.coctails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.coctails.databinding.ActivityMainBinding
import com.example.coctails.utils.Drinks
import com.example.coctails.utils.DrinksAdapter
import com.example.coctails.utils.DrinksResult
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var mNavController: NavController
    private var _binding: ActivityMainBinding? = null
    val mBinding get() = _binding!!
    private var jsonPlaceHolderApi: JsonPlaceHolderApi? = null
    var mDrinksResult : DrinksResult? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        mBinding.bottomNavigatinView.setupWithNavController(navController)


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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}