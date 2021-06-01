package com.example.covid_19tracker

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.covid_19tracker.vaccination.VaccineCentreActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            if(update("safe")==1){
                statuscardView.setCardBackgroundColor(Color.parseColor("#78C61E"))
                statustextview.text = "You Are Safe"
                emergencytxt.text = "No need to worry"
            }
            else if(update("safe")==2){
                statuscardView.setCardBackgroundColor(Color.parseColor("#C6190C"))
                statustextview.text = "You Need Medical Help"
                emergencytxt.text = "Em ergency"
            }
        }catch (e: Exception){

        }

        symptoms.setOnClickListener {
            symptoms.setTextColor(Color.parseColor("#32E0C4"))
            preventions.setTextColor(R.color.black)
            cardview1.visibility = View.VISIBLE
            cardview2.visibility = View.VISIBLE
            cardview3.visibility = View.VISIBLE
            cardview4.visibility = View.GONE
            cardview5.visibility = View.GONE
            cardview6.visibility = View.GONE
        }
        preventions.setOnClickListener {
            preventions.setTextColor(Color.parseColor("#32E0C4"))
            symptoms.setTextColor(R.color.black)
            cardview1.visibility = View.GONE
            cardview2.visibility = View.GONE
            cardview3.visibility = View.GONE
            cardview4.visibility = View.VISIBLE
            cardview5.visibility = View.VISIBLE
            cardview6.visibility = View.VISIBLE
        }
        testbtn.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }
        status.setOnClickListener {
            startActivity(Intent(this, com.example.covid_19tracker.status::class.java))
        }
        o2btn.setOnClickListener {
            startActivity(Intent(this, mypostActivity::class.java))
        }
        board.setOnClickListener {
            startActivity(Intent(this, publicpost::class.java))
        }
        callnowbtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:8800856394")
            startActivity(intent)
        }
        vaccineCenter.setOnClickListener {
            startActivity(Intent(this,VaccineCentreActivity::class.java))
        }

        notification.setOnClickListener {
            val array = arrayOf("On","Off")
           val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Notification Status")
            alertDialog.setSingleChoiceItems(array,0,object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when(array[which]){
                        "On" -> {
                            NotificationStatus(array[which])
                        }
                        "Off" ->{
                            NotificationStatus(array[which])
                        }
                    }
                }
            })
            alertDialog.setPositiveButton("ok",){dialogInterFace,which->
            }.create()
            val alert = alertDialog.create()
            alert.show()
        }
    }


    private fun NotificationStatus(status:String){
        val sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("status",status)
            apply()
        }
    }
    private fun update(key: String):Int{
        val sp = getSharedPreferences("mypref", Context.MODE_PRIVATE)
        return sp.getInt(key, 3)
    }
}

