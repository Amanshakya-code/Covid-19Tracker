package com.example.covid_19tracker

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.covid_19tracker.notification.Constant.Companion.chnageList
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_status.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.eazegraph.lib.models.PieModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
class status : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)
        //list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.list_header,list,false))
        if(hasInternetConnection())
        fetchData()
        else
        {
            Toast.makeText(this, "No internet Connection", Toast.LENGTH_LONG).show()
        }
        listofstatebtn.setOnClickListener {
            val intent = Intent(this, ListOfState::class.java)
            startActivity(intent)
        }
    }
    private fun fetchData(){
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){
                Client.api.clone().execute()//error difficulty
                //the main reason clone() exists is to allow multiple uses of Body objects (when they are one-use only.)
            }
            if(response.isSuccessful)
            {
                val data = Gson().fromJson(response.body?.string(), Response::class.java)
                launch(Dispatchers.Main) {
                    listofstatebtn.isEnabled = true
                    bindCombineData(data.statewise.get(0))
                    bindgraph(data.statewise.subList(1, data.statewise.size))
                }
            }
        }
    }

    private fun bindgraph(subList: List<StatewiseItem>) {
        chnageList = subList
        confirmcardView.setOnClickListener {
            val intent = Intent(this,barGraphActivity::class.java)
            intent.putExtra("condition","confirm")
            startActivity(intent)
        }
        recovercardView.setOnClickListener {
            val intent = Intent(this,barGraphActivity::class.java)
            intent.putExtra("condition","recovered")
            startActivity(intent)
        }
        activecardView.setOnClickListener {
            val intent = Intent(this,barGraphActivity::class.java)
            intent.putExtra("condition","active")
            startActivity(intent)
        }
        deceasedcardView.setOnClickListener {
            val intent = Intent(this,barGraphActivity::class.java)
            intent.putExtra("condition","death")
            startActivity(intent)
        }
    }


    private fun bindCombineData(data: StatewiseItem){
        val lastUpdateTime = data.lastupdatedtime
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:SS")
        lastUpdated.text = "Last Updated\n ${getTime(simpleDateFormat.parse(lastUpdateTime))}"
        confirmedTv.text = data.confirmed
        recoveredTv.text = data.recovered
        activeTv.text = data.active
        deceasedTv.text = data.deaths
        piechart.addPieSlice(data.confirmed?.let {
            PieModel(
                "Confirm", it.toFloat(), resources.getColor(
                    R.color.red
                )
            )
        })
        piechart.addPieSlice(data.active?.let {
            PieModel(
                "Active", it.toFloat(), resources.getColor(
                    R.color.blue
                )
            )
        })
        piechart.addPieSlice(data.recovered?.let {
            PieModel(
                "Recovered", it.toFloat(), resources.getColor(
                    R.color.green
                )
            )
        })
        piechart.addPieSlice(data.deaths?.let {
            PieModel(
                "Deceased", it.toFloat(), resources.getColor(
                    R.color.yellow
                )
            )
        })
        piechart.startAnimation()
    }
    fun getTime(past: Date):String
    {
        val now = Date()
        val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time - past.time)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time - past.time)
        val hours = TimeUnit.MILLISECONDS.toHours(now.time - past.time)

        return when {
            seconds < 60 -> {
                "Few seconds ago"
            }
            minutes < 60 -> {
                "$minutes minutes ago"
            }
            hours < 24 -> {
                "$hours hour ${minutes % 60} min ago"
            }
            else -> {
                SimpleDateFormat("dd/MM/yy, hh:mm a").format(past).toString()
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
}