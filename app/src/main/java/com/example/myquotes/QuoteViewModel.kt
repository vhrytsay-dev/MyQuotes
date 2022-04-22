package com.example.myquotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class QuoteViewModel : ViewModel() {
    var newQuoteAdded = false
    private val _quotes = MutableLiveData<MutableList<Quote>>().apply {
        value = mutableListOf()
    }

    var hasNoQuotes = Transformations.map(quotes){quotes.value.isNullOrEmpty()}

    val quotes: LiveData<MutableList<Quote>>
        get() = _quotes

        fun createQuote(text: String, author: String, year: String) {
            newQuoteAdded = true
            val quote = Quote(text, author, year)
            val list = _quotes.value ?: mutableListOf()
            list.add(quote)
            _quotes.value = list
        }
}