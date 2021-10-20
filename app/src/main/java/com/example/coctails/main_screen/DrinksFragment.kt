package com.example.coctails.main_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coctails.databinding.FragmentDrinksBinding

class DrinksFragment : Fragment() {

    private var _binding: FragmentDrinksBinding? = null
    val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDrinksBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment
        return mBinding.root
    }

}