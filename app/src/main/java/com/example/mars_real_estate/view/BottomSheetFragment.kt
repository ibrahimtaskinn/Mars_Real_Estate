package com.example.mars_real_estate.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.mars_real_estate.R
import com.example.mars_real_estate.databinding.BottomsheetDetailFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*
import kotlin.concurrent.schedule

class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomsheetDetailFragmentBinding
    private val navArgs: BottomSheetFragmentArgs? by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.bottomsheet_detail_fragment,
            container,
        false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        payNowButton()
    }

    private fun payNowButton() {
        navArgs?.let {
            binding.marsData = it.marsData
        }

        binding.payNowButton.setOnClickListener{
            binding.apply {
                payNowButton.visibility = View.INVISIBLE
                payConfirmed.visibility = View.VISIBLE
                Timer().schedule(1500) {
                    dismiss()
                }
            }
        }
    }
}