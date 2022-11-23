package com.example.mars_real_estate.adapter

import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mars_real_estate.BR
import com.example.mars_real_estate.databinding.RecyclerviewItemRowBinding
import com.example.mars_real_estate.model.MarsDataModel
import com.example.mars_real_estate.view.HomeListFragmentDirections


class MarsApiViewHolder(
    private val marsBinding: ViewDataBinding,
) : RecyclerView.ViewHolder(marsBinding.root) {
    fun onBind(marsDataModel: MarsDataModel) {
        val binding = marsBinding as RecyclerviewItemRowBinding

        /**
         * BR ile xmlde tanımlanan marsDataModelimize ulaşarak marsDataModel'ini set ediyoruz.
         */
        binding.apply {
            setVariable(BR.marsDataModel, marsDataModel)

            /**
             * Recyclerviewde ki image'a tıklandığında fieldDetailFragmentta yönlendirilmesi yapılıyor.
             * marsDataModel ile verileri de argument ile göndermiş oluyoruz.
             */
            marsImage.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(HomeListFragmentDirections.actionHomeListFragmentToFieldDetailFragment(
                        marsDataModel))

            }

        }
    }
}