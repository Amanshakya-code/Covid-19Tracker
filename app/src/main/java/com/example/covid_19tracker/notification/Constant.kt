package com.example.covid_19tracker.notification

import com.example.covid_19tracker.StatewiseItem

class Constant {
    companion object{
        const val BASE_URL = "https://fcm.googleapis.com/fcm/"
        const val SERVER_KEY = "AAAA6VzzfAw:APA91bGzxFyO64Dqvai_j2tR26UUcdq4oPdlN5HfrPg-VUahrxadXydyDNDjCakl_eQzcP4ngE_NTmMKJw7fjhwx_J5h0UrrS-jRbP5uOB_lznAiekTDwoJY0QYJPiLIEGWV3DQ6cyJP"
        const val CONTENT_TYPE = "application/json"
        var chnageList = listOf<StatewiseItem>()
        var subscribenotification = "/topics/covidtopic"
    }
}