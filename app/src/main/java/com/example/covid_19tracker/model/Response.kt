package com.example.covid_19tracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Response(
    val statewise: List<StatewiseItem>
)
@Entity(tableName = "stateData")
data class StatewiseItem(
    @PrimaryKey(autoGenerate = false)
    val id:Int? = null,
    @ColumnInfo(name = "statenotes")
    val statenotes: String? = null,
    @ColumnInfo(name = "recovered")
    val recovered: String? = null,
    @ColumnInfo(name = "deltadeaths")
    val deltadeaths: String? = null,
    @ColumnInfo(name = "migratedother")
    val migratedother: String? = null,
    @ColumnInfo(name = "deltarecovered")
    val deltarecovered: String? = null,
    @ColumnInfo(name = "active")
    val active: String? = null,
    @ColumnInfo(name = "deltaconfirmed")
    val deltaconfirmed: String? = null,
    @ColumnInfo(name = "deltaactive")
    val deltaactive: String? = null,
    @ColumnInfo(name = "state")
    val state: String? = null,
    @ColumnInfo(name = "statecode")
    val statecode: String? = null,
    @ColumnInfo(name = "confirmed")
    val confirmed: String? = null,
    @ColumnInfo(name = "deaths")
    val deaths: String? = null,
    @ColumnInfo(name = "lastupdatedtime")
    val lastupdatedtime: String? = null
)

