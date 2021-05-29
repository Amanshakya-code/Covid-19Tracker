package com.example.covid_19tracker

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    var danger = 0
    var safe = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        yes.setOnCheckedChangeListener{buttonView,isChecked->
            safe++
        }
        no.setOnCheckedChangeListener{buttonView,isChecked->
            danger++
        }
        Cough.setOnCheckedChangeListener{buttonView,isChecked->
            if(isChecked){
                danger++
            }else{
                danger--
            }
        }
        fever.setOnCheckedChangeListener{buttonView,isChecked->
            if(isChecked){
                danger++
            }else{
                danger--
            }
        }
        Cough.setOnCheckedChangeListener{buttonView,isChecked->
            if(isChecked){
                danger++
            }else{
                danger--
            }
        }
        brething.setOnCheckedChangeListener{buttonView,isChecked->
            if(isChecked){
                danger++
            }else{
                danger--
            }
        }
        smell.setOnCheckedChangeListener{buttonView,isChecked->
            if(isChecked){
                danger++
            }else{
                danger--
            }
        }
        none.setOnCheckedChangeListener{buttonView,isChecked->
            safe++
        }
        diabetes.setOnCheckedChangeListener{buttonView,isChecked->
            danger++
        }
        lung.setOnCheckedChangeListener{buttonView,isChecked->
            danger++
        }
        asthma.setOnCheckedChangeListener{buttonView,isChecked->
            danger++
        }
        heart.setOnCheckedChangeListener{buttonView,isChecked->
            danger++
        }
        yes2.setOnCheckedChangeListener{buttonView,isChecked->
            danger++
        }
        no2.setOnCheckedChangeListener{butttonView,isChecked->
            safe++
        }



        result.setOnClickListener {
            Log.i("lol","$safe")
            Log.i("lol","$danger")
            if(safe>=danger){

                SaveIntoSharePref("safe", 1)
                var dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("You Are Safe :)")
                dialogBuilder.setMessage("Please stay at home, maintain distance,wear mask and sanitize your hands")
                    .setCancelable(true)
                    .setPositiveButton("ok",){dialogInterFace,which->
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }.create()
                val alert = dialogBuilder.create()
                alert.show()
            }
            else{
                SaveIntoSharePref("safe", 2)
                var dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("You need medication")
                dialogBuilder.setMessage("Our advise to consult doctor as soon as possible\n No need to panic\n Precaution is better than cure:)")
                    .setCancelable(true)
                    .setPositiveButton("ok",){dialogInterFace,which->
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }.create()
                val alert = dialogBuilder.create()
                alert.show()
            }
        }
    }
    private fun SaveIntoSharePref(key:String,value:Int){
        val sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putInt(key,value)
            apply()
        }
    }

}