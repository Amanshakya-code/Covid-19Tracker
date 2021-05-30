package com.example.covid_19tracker.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19tracker.R
import com.example.covid_19tracker.adapter.postAdapter
import com.example.covid_19tracker.model.UserPost
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_publicpost.*

class publicpost : AppCompatActivity() {
    private lateinit var postList:ArrayList<UserPost>
    private  lateinit var adpater:postAdapter
    val database by lazy{
        FirebaseFirestore.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publicpost)

        recylerView.layoutManager = LinearLayoutManager(this)
        postList = arrayListOf()
        adpater = postAdapter(postList)
        recylerView.adapter = adpater
        EventChangeListener()
    }
    private fun EventChangeListener(){
        database.collection("users").orderBy("time",Query.Direction.DESCENDING).addSnapshotListener(object:EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.e("MESSAGEERE",error.message.toString())
                }
                else{
                    for(dc: DocumentChange in value?.documentChanges!!){
                        if(dc.type == DocumentChange.Type.ADDED){
                            postList.add(dc.document.toObject(UserPost::class.java))
                        }
                    }
                    adpater.notifyDataSetChanged()
                }
            }
        })
    }
}


