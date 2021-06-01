package com.example.covid_19tracker

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.covid_19tracker.LocalStorage.StateRoomDatabase
import com.example.covid_19tracker.mvvm.Viewmodel
import com.example.covid_19tracker.mvvm.ViewmodelProviderFactory
import com.example.covid_19tracker.mvvm.repository
import com.example.covid_19tracker.notification.Constant.Companion.chnageList
import com.example.covid_19tracker.vaccination.RetrofitVaccineInstance
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_status.*
import kotlinx.coroutines.*
import org.eazegraph.lib.models.PieModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
class status : AppCompatActivity() {
    lateinit var viewModel: Viewmodel
    var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)
        //list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.list_header,list,false))
        val repository = repository(StateRoomDatabase.getDatabaseInstance(this))
        val viewModelFactory = ViewmodelProviderFactory(application,repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(Viewmodel::class.java)
        viewModel.getsavedStatedata().observe(this, androidx.lifecycle.Observer {
            listofstatebtn.isEnabled = true
            try {
                bindCombineData(it.get(0))

                bindgraph(it.subList(1, it.size))
            }catch (e:Exception){
                Toast.makeText(this,"Please Wait We are Fetching the data for you",Toast.LENGTH_SHORT).show()
            }
        })
        listofstatebtn.setOnClickListener {
            val intent = Intent(this, ListOfState::class.java)
            startActivity(intent)
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
        counter++
        val lastUpdateTime = data.lastupdatedtime
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:SS")
        lastUpdated.text = "Last Updated\n ${getTime(simpleDateFormat.parse(lastUpdateTime))}"
        confirmedTv.text = data.confirmed
        recoveredTv.text = data.recovered
        activeTv.text = data.active
        deceasedTv.text = data.deaths

        Log.i("normalTag","called")
        if(counter==2) {
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
}