package com.example.mars_real_estate.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.mars_real_estate.R
import com.example.mars_real_estate.model.MarsDataModel

class MarsApiAdapter (
    private var marsArrayList : ArrayList<MarsDataModel> = ArrayList(),
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val marsBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.recyclerview_item_row, parent, false
        )

        return MarsApiViewHolder(marsBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MarsApiViewHolder).onBind(marsArrayList[position])
    }

    override fun getItemCount(): Int {
       return marsArrayList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMarsArrayList(marsArrayList: List<MarsDataModel>){
        this.marsArrayList.clear()
        this.marsArrayList.addAll(marsArrayList)
        notifyDataSetChanged()
    }

}