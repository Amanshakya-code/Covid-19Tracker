package com.example.covid_19tracker.authentication

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.covid_19tracker.MainActivity
import com.example.covid_19tracker.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var phoneNumber:String
    private lateinit var countryCode:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        phoneNumberEt.addTextChangedListener {
            sendcodebtn.isEnabled = !(it.isNullOrBlank() || it.length<10) // nextbtn only enable if the length is greater then 10 or it's not the blank edittext
        }
        sendcodebtn.setOnClickListener {
            checknumber()
        }
    }

    private fun checknumber() {
        countryCode = ccp.selectedCountryCodeWithPlus
        phoneNumber = countryCode+phoneNumberEt.text.toString()
        startActivity(Intent(this,OtpActivity::class.java).putExtra(PHONE_NUMBER,phoneNumber))
        finish()
    }
}