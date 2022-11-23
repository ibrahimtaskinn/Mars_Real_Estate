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
import com.example.mars_real_estate.databinding.FragmentFieldDetailBinding

class FieldDetailFragment : Fragment() {
    private lateinit var dataBinding: FragmentFieldDetailBinding
    private val navArgs: FieldDetailFragmentArgs? by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_field_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setMarsDataDetailsByArgs()
        bottomSheetController()
        backButtonController()
        imageFull()

    }

    /**
     * homeListFragmentta safe argument ile gönderdiğimiz verileri navArgs ile xml de verdiğimiz marsData verileri ile eşitliyoruz.
     */
    private fun setMarsDataDetailsByArgs() {
        navArgs?.let {
            dataBinding.marsData = it.marsData
        }
    }

    /**
     * buyNowButton butonuna bastığımızda açılan bottomSheetFragment'a yine safe argument ile marsDatalarını gönderiyoruz ve bottomSheetFragmentine geçiş yapıyoruz.
     */
    private fun bottomSheetController() {
        dataBinding.buyNowButton.setOnClickListener {
            navArgs?.let {
                findNavController().navigate(FieldDetailFragmentDirections.actionFieldDetailFragmentToBottomSheetFragment(
                    it.marsData))
            }
        }
    }

    private fun backButtonController() {
        dataBinding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun imageFull() {
        dataBinding.marsImage.setOnClickListener {
            navArgs?.let {
                findNavController().navigate(FieldDetailFragmentDirections.actionFieldDetailFragmentToFullSizeImageFragment(
                    it.marsData))
            }
        }
    }
}