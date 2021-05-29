package com.example.covid_19tracker.model

import java.util.*

data class UserPost (
    val name: String,
    val phonenumber: String,
    val address: String,
    val message: String,
    val time:Date = Date()
){
    constructor():this("","","","",Date())

}