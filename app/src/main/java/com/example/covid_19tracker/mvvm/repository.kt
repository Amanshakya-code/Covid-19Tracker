package com.example.covid_19tracker.mvvm

import com.example.covid_19tracker.Response
import com.example.covid_19tracker.model.VaccineCentre
import com.example.covid_19tracker.vaccination.RetrofitVaccineInstance

class repository {
    suspend fun getVaccineData(pincode:String,date:String):VaccineCentre{
        return RetrofitVaccineInstance.vaccineApi.getVaccineCentre(pincode,date)
    }
    suspend fun getStateData():Response{
        return RetrofitVaccineInstance.vaccineApi.getStateData()
    }
}