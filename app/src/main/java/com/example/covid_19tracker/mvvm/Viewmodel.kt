package com.example.covid_19tracker.mvvm

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid_19tracker.Response
import com.example.covid_19tracker.StatewiseItem
import com.example.covid_19tracker.model.VaccineCentre
import com.example.covid_19tracker.status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Viewmodel(app:Application,private val repository: repository):AndroidViewModel(app) {
    val VaccineData : MutableLiveData<VaccineCentre> = MutableLiveData()
   //val StateData : MutableLiveData<Response> = MutableLiveData()
    init {
        getStateData()
    }

    fun getVaccineData(pincode:String,date:String){
        viewModelScope.launch {
            try {
                if(hasInternetConnection()) {
                    val response = repository.getVaccineData(pincode, date)
                    VaccineData.postValue(response)
                }else{
                     launch(Dispatchers.Main) {
                         Toast.makeText(getApplication<applicationClass>(),"Please check your internet",Toast.LENGTH_LONG).show()
                     }
                }
            }catch (e:Exception){

            }

        }
    }
    fun getStateData(){
        viewModelScope.launch {
            try {
                if(hasInternetConnection()){
                    val response = repository.getStateData()
                    val statelist = response.statewise
                    saveStateData(statelist)
                }
                else{
                     launch(Dispatchers.Main) {
                        Toast.makeText(getApplication<applicationClass>(),"No Internet Connection",Toast.LENGTH_LONG).show()
                    }
                }
            }catch (e:java.lang.Exception){

            }

        }
    }
    fun getsavedStatedata() = repository.getStateRoomData()

    fun saveStateData(stateData:List<StatewiseItem>){
        viewModelScope.launch {
            repository.upsert(stateData)
        }
    }
    private fun hasInternetConnection():Boolean {
        val connectivityManager = getApplication<applicationClass>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)?:return false
            return when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
                else -> false
            }
        }else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}