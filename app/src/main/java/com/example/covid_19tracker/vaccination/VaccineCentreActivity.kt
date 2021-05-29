package com.example.covid_19tracker.vaccination

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19tracker.R
import com.example.covid_19tracker.adapter.VaccineCenterAdapter
import com.example.covid_19tracker.adapter.postAdapter
import com.example.covid_19tracker.model.CentersItem
import kotlinx.android.synthetic.main.activity_publicpost.*
import kotlinx.android.synthetic.main.activity_vaccine_centre.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class VaccineCentreActivity : AppCompatActivity() {
    lateinit var pincode:String
    lateinit var date:String

    lateinit var myCalendar: Calendar
    private lateinit var centerList:ArrayList<CentersItem>
    private  lateinit var adpater:VaccineCenterAdapter

    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccine_centre)
        searchbtncentre.setOnClickListener {
            pincode = pincodeedt.text.toString()

            myCalendar = Calendar.getInstance()

            dateSetListener =
                DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH, month)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val myformat = "dd-MM-yyyy"
                    val sdf = SimpleDateFormat(myformat)
                    Log.i("datelol","${sdf.format(myCalendar.time)}-->$pincode")
                    date = sdf.format(myCalendar.time)
                    centerPb.visibility = View.VISIBLE
                    fetchdata(pincode,date)
                }
            if(pincode.length < 6 || pincode.length>6){
                Toast.makeText(this,"Please Enter Valid Pincode",Toast.LENGTH_LONG).show()
            }else{
                val datePickerDialog = DatePickerDialog(
                    this, dateSetListener, myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.datePicker.minDate = System.currentTimeMillis()
                datePickerDialog.show()
            }

        }
        centreRecylerView.layoutManager = LinearLayoutManager(this)
        centerList = arrayListOf()
    }

    private fun fetchdata(pincode:String,date:String)  = CoroutineScope(Dispatchers.IO).launch  {

        try{
            val response = RetrofitVaccineInstance.vaccineApi.getVaccineCentre(pincode,date)
            val responseList = response.centers
            Log.i("responseVaccine","${response.centers}")
            launch(Dispatchers.Main){
                if(responseList!=null){
                    centerdoctorImage.visibility = View.GONE
                    centerPb.visibility = View.GONE
                    centerList.clear()
                    for(centerItem in responseList) {
                        centerList.add(centerItem)
                    }
                    adpater = VaccineCenterAdapter(centerList)
                    centreRecylerView.adapter = adpater
                }
            }

        }
        catch (e:Exception){
            Log.i("erroeorer",e.toString())
        }
    }
}