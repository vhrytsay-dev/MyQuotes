package com.example.myquotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myquotes.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.dialog_new_quote.*

class MainActivity : AppCompatActivity() {
    private lateinit var dialog: AlertDialog
    private lateinit var viewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel  = ViewModelProvider(this).get(QuoteViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val viewPager = binding.quoteViewPager
        val adapter = QuoteViewPagerAdapter()
        adapter.setQuotes(viewModel.quotes)
        viewPager.adapter = adapter

    }

    fun addQuote(view: View){
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle(R.string.new_quote_title)
            setView(R.layout.dialog_new_quote)
            setPositiveButton(R.string.ok) {dialog, id ->
                createQuote()
            }
            setNegativeButton(R.string.abort, null)
        }
        dialog = builder.create()
        dialog.show()
    }

    private fun createQuote() {
        val text = dialog.dialog_quote_text.text.toString()
        val author= dialog.dialog_quote_author.text.toString()
        val year = dialog.dialog_quote_year.text.toString()
        viewModel.createQuote(text, author, year)
    }
}