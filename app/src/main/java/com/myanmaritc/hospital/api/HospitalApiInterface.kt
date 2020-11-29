package com.myanmaritc.hospital.api

import com.myanmaritc.hospital.model.Hospital
import com.myanmaritc.hospital.model.HospitalsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HospitalApiInterface {

    @GET("hospital")
    fun getAllHospital(): Call<Hospital>

    @GET("hospital/{id}")
    fun getDetail(
        @Path("id") id : String
    ): Call<com.myanmaritc.hospital.model2.HospitalsItem>
}