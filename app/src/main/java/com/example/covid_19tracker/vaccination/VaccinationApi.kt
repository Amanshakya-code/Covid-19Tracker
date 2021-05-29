package com.example.covid_19tracker.vaccination

import com.example.covid_19tracker.model.VaccineCentre
import com.example.covid_19tracker.notification.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val base_Url = "https://cdn-api.co-vin.in"
interface VaccinationApi {
    @GET("/api/v2/appointment/sessions/public/calendarByPin")
    suspend fun getVaccineCentre(
        @Query("pincode")
        pinCode:String,
        @Query("date")
        date:String
    ):VaccineCentre
    @GET("https://api.covid19india.org/data.json")
    suspend fun getStateData():com.example.covid_19tracker.Response
}