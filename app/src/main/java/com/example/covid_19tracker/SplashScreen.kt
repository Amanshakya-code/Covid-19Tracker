package com.example.covid_19tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covid_19tracker.authentication.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    val auth by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(auth.currentUser == null)
        {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        else
        {
            startActivity(Intent(this,MainActivity::class.java))
        }
        finish()
    }
}