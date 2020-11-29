package com.myanmaritc.hospital.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.myanmaritc.hospital.R
import com.myanmaritc.hospital.api.ApiClient
import com.myanmaritc.hospital.fragment.adapter.HospitalAdapter
import com.myanmaritc.hospital.model.HospitalsItem
import com.myanmaritc.hospital.model2.Hospital
import com.myanmaritc.hospital.viewmodel.DetailViewmodel
import com.myanmaritc.hospital.viewmodel.HospitalViewmodel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailFragment : Fragment() {

    lateinit var detailViewmodel: DetailViewmodel
    lateinit var detailAdapter: HospitalAdapter

    var baseImg = "http://hospitalguideapi.dsv.hoz.mybluehost.me"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewmodel = ViewModelProvider(this)
            .get(DetailViewmodel::class.java)

        var detailIdArgs = arguments?.let {
            DetailFragmentArgs.fromBundle(it)
        }
        var detailId = detailIdArgs?.id

//        detailViewmodel.loadDetail(detailId.toString())
//
//        detailViewmodel.getDetail()


       hospitalId.text = detailId

        val apiClient = ApiClient()

        val apiCall = apiClient.getDetail(detailId.toString())

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
                txtName.text = response.body()?.hospital?.hospitalName.toString()
                hospitalId.text = response.body()?.hospital?.place
                hospitalPlace.text = response.body()?.hospital?.hospitalId.toString()
                Picasso.get()
                    .load(baseImg + response.body()?.hospital?.hospitalImage)
                    .into(imgHospital)


            }

        })


    }




}