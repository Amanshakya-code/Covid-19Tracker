package com.example.covid_19tracker.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid_19tracker.Response
import com.example.covid_19tracker.model.VaccineCentre
import kotlinx.coroutines.launch

class Viewmodel(private val repository: repository):ViewModel() {
    val VaccineData : MutableLiveData<VaccineCentre> = MutableLiveData()
    val StateData : MutableLiveData<Response> = MutableLiveData()
    init {
        getStateData()
    }

    fun getVaccineData(pincode:String,date:String){
        viewModelScope.launch {
            val response = repository.getVaccineData(pincode,date)
            VaccineData.postValue(response)
        }
    }
    fun getStateData(){
        viewModelScope.launch {
            val response = repository.getStateData()
            StateData.postValue(response)
        }
    }
}