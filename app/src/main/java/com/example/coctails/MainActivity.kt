package com.example.coctails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.coctails.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.example.coctails.screens.CocktailsFragment
import com.example.coctails.screens.DrinksFragment

import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var mNavController: NavController
    private var _binding: ActivityMainBinding? = null
    val mBinding get() = _binding!!
    private val drinksFragment = DrinksFragment()
    private val cocktailsFragment = CocktailsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        mBinding.bottomNavigationView.setupWithNavController(navController)


        //it was step 1 here
        val retrofit = Retrofit.Builder()
            .baseUrl("https://thecocktaildb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // calling the action bar

        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.drink_menu -> replaceFragment(drinksFragment)
                R.id.cocktail_menu -> replaceFragment(cocktailsFragment)
            }
            true
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, fragment)
            transaction.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}