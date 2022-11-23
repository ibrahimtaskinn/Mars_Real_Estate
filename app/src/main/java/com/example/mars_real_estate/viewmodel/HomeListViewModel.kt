package com.example.mars_real_estate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mars_real_estate.model.MarsDataModel
import com.example.mars_real_estate.service.MarsApi
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


class HomeListViewModel : ViewModel() {

    val marsLoading = MutableLiveData<Boolean>()
    private var marsValue1 = MutableLiveData<List<MarsDataModel>>()
    val marsValue: LiveData<List<MarsDataModel>> =marsValue1

    /**
     * @return mutablelivedata dan gelen marsdatamodel listteki verileri dönüyoruz. Bu sayede getMarsData() çağırıldığında marsValuedeki verileri kullanabiliyoruz.
     */
    fun getMarsData(): LiveData<List<MarsDataModel>> {
        marsLoading.value = true
        MarsApi.retrofitService.getProperties().enqueue(object : Callback<List<MarsDataModel>> {
            override fun onResponse(
                call: Call<List<MarsDataModel>>,
                response: Response<List<MarsDataModel>>
            ) {
                marsValue1.value = response.body()
                marsLoading.value = false
            }

            override fun onFailure(call: Call<List<MarsDataModel>>, t: Throwable) {
                marsLoading.value = true
            }

        })

        return marsValue
    }


}