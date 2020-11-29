package com.myanmaritc.hospital.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myanmaritc.hospital.api.ApiClient
import com.myanmaritc.hospital.model.HospitalsItem
import kotlinx.android.synthetic.main.fragment_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailViewmodel: ViewModel() {

    private var hospitalItem : MutableLiveData<com.myanmaritc.hospital.model2.HospitalsItem> = MutableLiveData()

    fun  getDetail() : MutableLiveData<com.myanmaritc.hospital.model2.HospitalsItem> = hospitalItem

    fun loadDetail(id: String){

        val apiClient = ApiClient()

        val apiCall = apiClient.getDetail(id)

        apiCall.enqueue(object : Callback<com.myanmaritc.hospital.model2.HospitalsItem>{
            override fun onFailure(
                call: Call<com.myanmaritc.hospital.model2.HospitalsItem>,
                t: Throwable
            ) {

            }

            override fun onResponse(
                call: Call<com.myanmaritc.hospital.model2.HospitalsItem>,
                response: Response<com.myanmaritc.hospital.model2.HospitalsItem>
            ) {
                //txtName.text = response.body().toString()
               hospitalItem.value = response.body()

            }

        })




    }
}
