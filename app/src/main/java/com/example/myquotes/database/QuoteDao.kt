package com.example.myquotes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myquotes.Quote

@Dao
interface QuoteDao {

    @Insert
    suspend fun insert(query: Quote)

    @Query("SELECT * from quotes ORDER BY id ASC")
    fun getQuotes(): LiveData<List<Quote>>
}