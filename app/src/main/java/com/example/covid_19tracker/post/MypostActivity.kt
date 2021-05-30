package com.example.covid_19tracker.post

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.covid_19tracker.R
import com.example.covid_19tracker.detail
import com.example.covid_19tracker.model.UserPost
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_mypost.*
import java.lang.Exception
import java.text.SimpleDateFormat

class mypostActivity : AppCompatActivity() {
    val database by lazy{
        FirebaseFirestore.getInstance()
    }
    val auth by lazy{
        FirebaseAuth.getInstance()
    }
    lateinit var currentProfile:UserPost
    private val sdf = SimpleDateFormat("dd/MM/yyyy \nHH:mm:ss")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypost)
        createpostbtn.setOnClickListener {
            startActivity(Intent(this, detail::class.java))
        }

            database.collection("users").document(auth.uid!!).get().addOnSuccessListener {
                try {
                    currentProfile = it.toObject(UserPost::class.java)!!
                    namepost.text = currentProfile.name
                    phonenumberpost.text = currentProfile.phonenumber
                    addresspost.text = currentProfile.address
                    messagepost.text = currentProfile.message
                    time.text = sdf.format(currentProfile.time)
                    mypostcv.visibility = View.VISIBLE
                    deletePost.isEnabled = true
                }catch (e:Exception){
                    var dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("How This Works ?")
                    dialogBuilder.setMessage("1. YOU CAN POST YOUR PROBLEM HERE\n2. PEOPLE WILL SEE YOUR POST AND REVERT BACK\n3. PLEASE ENTER YOUR CORRECT INFO.\n4. YOU CAN ONLY CREATE ONE POST AT A TIME\n5. NO SCAM,NO SPAM OR ELSE YOU WILL BE BLOCKED")
                        .setCancelable(false)
                        .setPositiveButton("ok",){dialogInterFace,which->
                        }.create()
                    val alert = dialogBuilder.create()
                    alert.show()
                }

            }.addOnFailureListener{

            }
        deletePost.setOnClickListener {
            var dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Delete")
            dialogBuilder.setMessage("Do you want to delete this post \nThis post will disappear from post pannel")
                .setCancelable(true)
                .setPositiveButton("delete",){dialogInterFace,which->
                    database.collection("users").document(auth.uid!!).delete().addOnSuccessListener {
                        mypostcv.visibility = View.INVISIBLE
                        deletePost.isEnabled = false
                        Toast.makeText(this,"Post has been deleted successfully",Toast.LENGTH_SHORT).show()
                    }
                }.create()
            val alert = dialogBuilder.create()
            alert.show()

        }
    }
}