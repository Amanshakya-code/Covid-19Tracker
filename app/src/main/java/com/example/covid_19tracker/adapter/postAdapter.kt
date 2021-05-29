package com.example.covid_19tracker.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.ParseException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19tracker.R
import com.example.covid_19tracker.model.UserPost
import kotlinx.android.synthetic.main.single_publicpost.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class postAdapter(private val postList:ArrayList<UserPost>):RecyclerView.Adapter<postAdapter.myPostViewHolder>() {
    private val sdf = SimpleDateFormat("dd/MM/yyyy \nHH:mm:ss")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): postAdapter.myPostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_publicpost,parent,false)
        return myPostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: postAdapter.myPostViewHolder, position: Int) {
        val post = postList[position]
        holder.namePost.text = post.name
        holder.phonenumber.text = post.phonenumber
        holder.address.text = post.address
        holder.message.text = post.message
        holder.time.text = sdf.format(post.time)
        holder.card.setOnLongClickListener {
            val shareIntent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT,"Help Me\n"+post.name+"\n"+"Phone no.:- "+post.phonenumber+"\n"+"Address:- "+post.address+"\nMessage:- "+post.message)
                this.type = "text/plain"
            }
            holder.card.context.startActivity(shareIntent)
            true
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }
    class myPostViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var namePost = itemView.findViewById<TextView>(R.id.namepost)
        var phonenumber = itemView.findViewById<TextView>(R.id.phonenumberpost)
        var address = itemView.findViewById<TextView>(R.id.addresspost)
        var message = itemView.findViewById<TextView>(R.id.messagepost)
        var time = itemView.findViewById<TextView>(R.id.time)
        var card = itemView.findViewById<CardView>(R.id.mypostcv)

    }
}