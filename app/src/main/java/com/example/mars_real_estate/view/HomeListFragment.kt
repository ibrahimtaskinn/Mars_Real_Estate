package com.example.mars_real_estate.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide.init
import com.example.mars_real_estate.R
import com.example.mars_real_estate.adapter.MarsApiAdapter
import com.example.mars_real_estate.databinding.FragmentHomeListBinding
import com.example.mars_real_estate.util.SharedPreferencesManager
import com.example.mars_real_estate.viewmodel.HomeListViewModel

class HomeListFragment : Fragment() {
    private lateinit var binding: FragmentHomeListBinding
    private lateinit var sharedPreferenceManager: SharedPreferencesManager
    private val homeListViewModel: HomeListViewModel by viewModels()
    private var adapter: MarsApiAdapter = MarsApiAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        context?.let {
            sharedPreferenceManager = SharedPreferencesManager(it)
        }

        getMarsApiData()
        refreshData()
        logOutButton()
        loadingData()
    }

    /**
     * homeListViewModel içindeki marsValueden gelen livedataları observe ederek recyclerview içerisine ekliyoruz.
     * recyclerview'i gridLayoutManager ile 2'li grid yapıda olmasını sağlıyoruz.
     * BR ile de xml de verilen adapter datasına ulaşarak verileri setliyoruz.
     */
    private fun getMarsApiData() {
        homeListViewModel.getMarsData().observe(viewLifecycleOwner) { marsValue ->
           adapter.setMarsArrayList(marsValue)
           val gridLayoutManager = GridLayoutManager(context, 2)
            binding.recyclerview.layoutManager = gridLayoutManager
            binding.recyclerview.adapter = adapter
        }
    }

    /**
     * xml de verdiğimiz swipeRefreshLayout'u refresh atıldığında olacak durumları belirliyoruz. HomeLisViewModel.getMarsData() ile de verileri tekrar apiden çekiliyor.
     */
    private fun refreshData() {
        binding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                recyclerview.visibility = View.INVISIBLE
                marsLoading.visibility = View.INVISIBLE
                homeListViewModel.getMarsData()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    /**
     * xml de verdiğimiz ProgressBar'ın durumunu belirliyoruz. Observe ile homeListViewModel da tanımlanan loading boolean değerini alarak gerekli düzenlemeleri sağlıyoruz.
     */
    private fun loadingData() {
        homeListViewModel.marsLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                binding.apply {
                    if (it){
                        marsLoading.visibility = View.VISIBLE
                        recyclerview.visibility = View.INVISIBLE
                    } else {
                        recyclerview.visibility = View.VISIBLE
                        marsLoading.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun logOutButton() {
       binding.logOutButton.setOnClickListener {
           sharedPreferenceManager.saveCheckStarted(false)
           findNavController().navigate(R.id.action_homeListFragment_to_getStarted)
       }
    }
}