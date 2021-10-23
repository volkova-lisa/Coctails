package com.example.coctails.screens


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.coctails.R
import com.example.coctails.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenFragment : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    val mBinding get() = _binding!!
    val splashScreenScope = CoroutineScope(Dispatchers.Main)
    private var progressBar: ProgressBar? = null
    private var i = 0
    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)

        splashScreenScope.launch {
            mBinding.pBar.visibility = View.VISIBLE
            i = mBinding.pBar.progress
                while (i < 4) {
                    i += 1
                    handler.post(Runnable {
                        mBinding.pBar.progress = i
                    })
                    try {
                        delay(500)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
            }
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, DrinksFragment())
            transaction.commit()
        }
        return mBinding.root
    }

}