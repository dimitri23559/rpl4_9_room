package com.example.rpl4_9_room.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class movieDb : RoomDatabase(){
    abstract fun movieDao() : MovieDao

    companion object {
        @Volatile private var instance : movieDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            movieDb::class.java,
            "movie12345.db"
        ).build()
    }
}