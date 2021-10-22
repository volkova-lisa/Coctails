package com.example.coctails.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coctails.databinding.FragmentDrinksBinding
import com.example.coctails.utils.Drinks
import com.example.coctails.utils.DrinksAdapter
import com.example.coctails.utils.DrinksResult

class DrinksFragment : Fragment() {

    private var _binding: FragmentDrinksBinding? = null
    val mBinding get() = _binding!!
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: DrinksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDrinksBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment
        return mBinding.root
    }


    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mAdapter = DrinksAdapter()
        mRecyclerView = mBinding.recyclerView
        mRecyclerView.adapter = mAdapter

        //val list = DrinksResult
        //mAdapter.setList(list)
    }

    companion object {
        fun click(drink: Drinks){
            val bundle = Bundle()
            bundle.putSerializable("drink", drink)
        }
    }

}