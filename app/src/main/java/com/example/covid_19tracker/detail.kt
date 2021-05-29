package com.example.covid_19tracker

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.covid_19tracker.model.UserPost
import com.example.covid_19tracker.notification.PushNotification
import com.example.covid_19tracker.notification.RetrofitInstance
import com.example.covid_19tracker.notification.notificationData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_mypost.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
const val TOPIC = "/topics/covidtopic"
class detail : AppCompatActivity() {
    val auth by lazy{
        FirebaseAuth.getInstance()
    }
    val database by lazy{
        FirebaseFirestore.getInstance()
    }
    var namecurrent = ""
    var phonenumber = ""
    var addresscurrent =""
    var messagecurrent = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        if(hasInternetConnection())
            post.isEnabled = true
        else {
            post.isEnabled = false
            Toast.makeText(this,"No Intenet Connection",Toast.LENGTH_LONG).show()
        }
        post.setOnClickListener {
            val date = Date()
            namecurrent = name.editText?.text.toString()
            phonenumber = phone.editText?.text.toString()
            addresscurrent = address.editText?.text.toString()
            messagecurrent = message.editText?.text.toString()
            Log.i("lolol","$namecurrent---$phonenumber---$addresscurrent----$messagecurrent")
            if(namecurrent.isNotEmpty() && phonenumber.isNotEmpty() && addresscurrent.isNotEmpty() && messagecurrent.isNotEmpty()){
                var post = UserPost(namecurrent,phonenumber,addresscurrent,messagecurrent,date)
                database.collection("users").document(auth.uid!!).set(post).addOnSuccessListener {
                    Toast.makeText(this,"complete",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,MainActivity::class.java))
                    createNotification()
                }
            }else{
                Toast.makeText(this,"Please provide all information",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun createNotification() {
        if(namecurrent.isNotEmpty()&&phonenumber.isNotEmpty()&&addresscurrent.isNotEmpty()&&messagecurrent.isNotEmpty()){
            PushNotification(
                notificationData(namecurrent," Needs Your Help",messagecurrent),
                TOPIC
            ).also {
                sendNotification(it)
            }
        }
    }
    private fun hasInternetConnection():Boolean {
        val connectivityManager = getApplication().getSystemService(
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

    private fun sendNotification(notification:PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try{
            val response = RetrofitInstance.api.postNotification(notification)
            if(response.isSuccessful){
                // Log.i("response","response: ${Gson().toJson(response)}")
            }
            else
            {
                Log.i("responsefaild",response.errorBody().toString())
            }
        }
        catch (e:Exception){
            Log.i("reerror",e.toString())
        }
    }
}