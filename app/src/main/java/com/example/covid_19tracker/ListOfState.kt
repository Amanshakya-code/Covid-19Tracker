package com.example.covid_19tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.covid_19tracker.adapter.stateAdapter
import com.example.covid_19tracker.notification.Constant.Companion.chnageList
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_list_of_state.*
import kotlinx.android.synthetic.main.activity_status.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Collections.addAll

class ListOfState : AppCompatActivity() {
    lateinit var stateAdapter: stateAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_state)
        list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.list_header,list,false))
        stateAdapter = stateAdapter(chnageList)
        list.adapter = stateAdapter
    }
}