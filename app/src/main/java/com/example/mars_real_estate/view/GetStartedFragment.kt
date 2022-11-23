package com.example.mars_real_estate.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Looper.myLooper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.mars_real_estate.R
import com.example.mars_real_estate.databinding.FragmentGetStartedBinding
import com.example.mars_real_estate.util.SharedPreferencesManager

class GetStartedFragment : Fragment() {
    private lateinit var binding: FragmentGetStartedBinding
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_started, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init(){
        context?.let {
            sharedPreferencesManager = SharedPreferencesManager(it)
        }

        //Text Html
        binding.getStartedTitle.text =
            HtmlCompat.fromHtml(getString(R.string.html), HtmlCompat.FROM_HTML_MODE_LEGACY)

        getStartedShared()
    }

    /**
     * SharedPrefence ile getStartedFragmenttaki butona kullanıcı bir defa bastıktan sonra her girişte splash ekran gibi 1 saniye gösteriliyor sonra homeListFragmenta geçişi sağlanıyor.
     */
    private fun getStartedShared() {
        if (sharedPreferencesManager.getCheckStarted()) {
            binding.getStartedButton.visibility = View.INVISIBLE
            Looper.myLooper()?.let {
                Handler(it).postDelayed({
                    findNavController().navigate(R.id.action_getStarted_to_homeListFragment)
                }, 1000)
            }
        } else {
            binding.getStartedButton.setOnClickListener{
                sharedPreferencesManager.saveCheckStarted(true)
                findNavController().navigate(R.id.action_getStarted_to_homeListFragment)
            }
        }
    }
}