package com.example.covid_19tracker.vaccination

import com.example.covid_19tracker.notification.NotificationApi
import com.example.covid_19tracker.notification.RetrofitInstance
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitVaccineInstance {
    companion object{
        private val retrofitVaccineInstance by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl("https://cdn-api.co-vin.in")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val vaccineApi by lazy {
            retrofitVaccineInstance.create(VaccinationApi::class.java)
        }
    }
}