package com.example.covid_19tracker.LocalStorage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.covid_19tracker.StatewiseItem

@Dao
interface StateDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(statedata:List<StatewiseItem>)
    @Query("SELECT * FROM stateData")
    fun getallStatedata():LiveData<List<StatewiseItem>>
    @Update()
    suspend fun updateStateList(statedata: List<StatewiseItem>)
}