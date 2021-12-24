package com.example.cricketscorecount.sevices.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [TeamName::class], version = 1, exportSchema = false)
abstract class TeamDatabase: RoomDatabase() {

    abstract fun teamDao(): TeamDao

    companion object{
        private var INSTANCE: TeamDatabase? = null

        fun getDatabase(context: Context): TeamDatabase{
            val teamInstance = INSTANCE
            if (teamInstance != null){
                return teamInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeamDatabase::class.java,
                    "Team_info"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }
}