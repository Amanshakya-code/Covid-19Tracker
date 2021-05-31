package com.example.covid_19tracker.LocalStorage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.covid_19tracker.StatewiseItem

@Database(entities = [StatewiseItem::class],version = 7,exportSchema = false)
abstract class StateRoomDatabase:RoomDatabase() {
    abstract fun getStateDao(): StateDAO
    companion object{
        @Volatile
        private var INSTANCE:StateRoomDatabase? = null
        fun getDatabaseInstance(context:Context):StateRoomDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,StateRoomDatabase::class.java,"stateDb").fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE!!
        }
    }
}