package com.myanmaritc.hospital.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myanmaritc.hospital.api.ApiClient
import com.myanmaritc.hospital.model.Hospital
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospitalViewmodel : ViewModel() {

    private var hospital:MutableLiveData<Hospital> = MutableLiveData()

    fun getHospital() = hospital

    fun loadHospital(){
        
        val apiClient = ApiClient()
        val apiCall = apiClient.getAllHospital()

        apiCall.enqueue(object : Callback<Hospital>{
            override fun onFailure(call: Call<Hospital>, t: Throwable) {

            }

            override fun onResponse(call: Call<Hospital>, response: Response<Hospital>) {
                hospital.value = response.body()
            }

        })
    }
}