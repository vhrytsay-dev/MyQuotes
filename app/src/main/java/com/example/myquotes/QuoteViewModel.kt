package com.example.myquotes

import android.app.Application
import androidx.lifecycle.*
import com.example.myquotes.database.AppDatabase
import kotlinx.coroutines.launch

class QuoteViewModel(application: Application) : AndroidViewModel(application) {
    var newQuoteAdded = false
    private var quoteDao = AppDatabase.getDatabase(application).quoteDao()
    val quotes = quoteDao.getQuotes()

    var hasNoQuotes = Transformations.map(quotes){quotes.value.isNullOrEmpty()}

    fun createQuote(text: String, author: String, year: String) = viewModelScope.launch {
        newQuoteAdded = true
        quoteDao.insert(Quote(text, author, year))
    }

}