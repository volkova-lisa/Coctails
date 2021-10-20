package com.example.coctails


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.coctails.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenFragment : Fragment() {


    private var _binding: FragmentSplashScreenBinding? = null
    val mBinding get() = _binding!!
    val splashScreenScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.splashScreenFragment, MainScreenFragment())
        transaction.commit()
//        mBinding.go.setOnClickListener {
//            Navigation.createNavigateOnClickListener(R.id.action_splashScreenFragment_to_mainScreenFragment)
//        val transaction = requireActivity().supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.splashScreenFragment, MainScreenFragment())
//
//        }
//        splashScreenScope.launch {
//            delay(2000)
//        }
        return mBinding.root
    }

}