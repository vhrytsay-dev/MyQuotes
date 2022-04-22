package com.example.myquotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myquotes.Quote


@Database(entities = [Quote::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun quoteDao(): QuoteDao

    companion object{
        @Volatile private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase{
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "quotes.db"
                ).build()
            }
            return instance!!
        }
    }
}