package com.example.mars_real_estate.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mars_real_estate.R
import com.example.mars_real_estate.databinding.FragmentFullSizeImageBinding

class FullSizeImageFragment : Fragment() {
    private lateinit var binding: FragmentFullSizeImageBinding
    private val navArgs: FullSizeImageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_full_size_image, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageFullSize()
        backButton()
    }

    /**
     * argumentten gelen verileri xml de tanımladığımız marsData değerine eşitleyerek imageView de tıklanan görseli gösteriyoruz.
     */
    private fun imageFullSize() {
        navArgs.let {
            binding.marsData = it.marsData
        }
    }

    private fun backButton() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}