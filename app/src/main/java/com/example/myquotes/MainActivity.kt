package com.example.myquotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val quotes = listOf(
        Quote(
            "Probleme kann man niemals mit derselben Denkweise lösen, durch die sie entsranden sind.",
            "Albert Einstein",
            "1948"
        ),
        Quote(
            "Man braucht nichts im Leben zu fürchten, man muss nur alles verstehen.",
            "Marie Curie",
            "1928"
        ),
        Quote(
            "Nichts ist so beständig wie der Wandel",
            "Heraklit",
            "480 v. Chr."
        )
    )
    private var index = 0
    private lateinit var quoteText: TextView
    private lateinit var quoteAuthor: TextView
    private lateinit var quoteYear: TextView
    private lateinit var previous: Button
    private lateinit var next: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quoteText = findViewById<TextView>(R.id.quote_text)
        quoteAuthor = findViewById<TextView>(R.id.quote_author)
        quoteYear = findViewById<TextView>(R.id.quote_year)
        previous = findViewById(R.id.quote_previous)
        next = findViewById(R.id.quote_next)

        showQuote()
        setVisibilityOfButtons()
    }

    fun nextQuote(view: View) {
        if(index < quotes.size -1) index++
        showQuote()
        setVisibilityOfButtons()
    }

    fun previousQuote(view: View) {
        if(index > 0) index--
        showQuote()
        setVisibilityOfButtons()
    }
    private fun showQuote() {
        quoteText.text = quotes[index].text
        quoteAuthor.text = quotes[index].author
        quoteYear.text = quotes[index].year
    }

    private fun setVisibilityOfButtons() = when (index) {
            0 -> {
                previous.visibility = View.INVISIBLE
                next.visibility = if(quotes.size > 1) View.VISIBLE else View.INVISIBLE
            }
            quotes.size - 1 -> {
                next.visibility = View.INVISIBLE
                previous.visibility = if(quotes.size > 1) View.VISIBLE else View.INVISIBLE
            }
            else -> {
                next.visibility = View.VISIBLE
                previous.visibility = View.VISIBLE
            }
        }
}