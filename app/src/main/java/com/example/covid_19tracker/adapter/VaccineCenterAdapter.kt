package com.example.covid_19tracker.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19tracker.R
import com.example.covid_19tracker.model.CentersItem
import com.example.covid_19tracker.model.VaccineCentre

class VaccineCenterAdapter(private val centerList:ArrayList<CentersItem>):RecyclerView.Adapter<VaccineCenterAdapter.VaccineViewHolder>() {
    class VaccineViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val centerName = itemView.findViewById<TextView>(R.id.centreName)
        val centerAddress = itemView.findViewById<TextView>(R.id.address)
        val centerState = itemView.findViewById<TextView>(R.id.state)
        val centerDistrict = itemView.findViewById<TextView>(R.id.district)
        val timecenter = itemView.findViewById<TextView>(R.id.timecenter)
        val fee = itemView.findViewById<TextView>(R.id.fee)
        val age = itemView.findViewById<TextView>(R.id.age)
        val vaccine = itemView.findViewById<TextView>(R.id.vaccine)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VaccineCenterAdapter.VaccineViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_vaccinated_item,parent,false)
        return VaccineCenterAdapter.VaccineViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VaccineCenterAdapter.VaccineViewHolder, position: Int) {
        val post = centerList[position]
        holder.centerName.text = post.name
        holder.centerAddress.text = post.address
        holder.centerState.text = post.state_name
        holder.centerDistrict.text = post.district_name
        holder.timecenter.text = post.from+"->"+post.to
        holder.fee.text = post.fee_type
        holder.age.text = post.sessions?.get(0)?.min_age_limit.toString()
        holder.vaccine.text = post.sessions?.get(0)?.vaccine.toString()
    }

    override fun getItemCount(): Int {
        return centerList.size
    }
}