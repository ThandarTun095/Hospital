package com.myanmaritc.hospital.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myanmaritc.hospital.R
import com.myanmaritc.hospital.model.HospitalsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.item_hospital.view.*
import kotlinx.android.synthetic.main.item_hospital.view.hospitalName

class HospitalAdapter : RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>() {

    private var hospitalItems : List<HospitalsItem> = ArrayList()

    var clickListener: ClickListener? = null

    var baseImg = "http://hospitalguideapi.dsv.hoz.mybluehost.me"


    inner class HospitalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
    View.OnClickListener{


        init {
            itemView.setOnClickListener(this)
        }

        lateinit var hospitalsItem: HospitalsItem

        fun bind(hospitalsItem: HospitalsItem){

            this.hospitalsItem = hospitalsItem

            Picasso.get()
                .load(baseImg + hospitalsItem.hospitalImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.imgBackground)

            itemView.hospitalName.text = hospitalsItem.hospitalName
        }

        override fun onClick(view: View?) {
        clickListener?.onClick(hospitalsItem)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hospital,parent,false)

        return HospitalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hospitalItems.size
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        holder.bind(hospitalItems[position])
    }

    fun updateHospital(hospitalList: List<HospitalsItem>){
        this.hospitalItems = hospitalList
        notifyDataSetChanged()
    }

    interface  ClickListener {
        fun onClick(hospitalsItem: HospitalsItem)
    }

    fun setOnClickListener(clickListener: ClickListener){
        this.clickListener = clickListener
    }

}