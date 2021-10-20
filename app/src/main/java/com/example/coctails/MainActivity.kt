package com.example.coctails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.coctails.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mNavController: NavController
    private var _binding: ActivityMainBinding? = null
    val mBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


       // mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        //mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
//        navController.navigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        //это нужно чтобы избежать утечки памяти!! запомнить*
        _binding = null
    }
}