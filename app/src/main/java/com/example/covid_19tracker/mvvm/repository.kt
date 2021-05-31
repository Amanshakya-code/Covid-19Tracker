package com.example.covid_19tracker.mvvm

import com.example.covid_19tracker.LocalStorage.StateRoomDatabase
import com.example.covid_19tracker.Response
import com.example.covid_19tracker.StatewiseItem
import com.example.covid_19tracker.model.VaccineCentre
import com.example.covid_19tracker.vaccination.RetrofitVaccineInstance

class repository (val db:StateRoomDatabase){
    suspend fun getVaccineData(pincode:String,date:String):VaccineCentre{
        return RetrofitVaccineInstance.vaccineApi.getVaccineCentre(pincode,date)
    }
    suspend fun getStateData():Response{
        return RetrofitVaccineInstance.vaccineApi.getStateData()
    }
    fun getStateRoomData() = db.getStateDao().getallStatedata()

    suspend fun upsert(statedata:List<StatewiseItem>) {
        db.getStateDao().upsert(statedata)
    }
    suspend fun update(statedata: List<StatewiseItem>){
        db.getStateDao().updateStateList(statedata)
    }
}