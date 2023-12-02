package com.example.jokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.ListView
import android.widget.ArrayAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var lv_categories: ListView
    private lateinit var btn_jokes: Button
    private lateinit var tv_jokes: TextView
    private lateinit var progressBar1: ProgressBar
    private lateinit var progressBar2: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the button view by its ID and
        // assign it to a variable.
        btn_jokes = findViewById(R.id.btn_joke)

        // Find the text view by its ID and
        // assign it to a variable.
        tv_jokes = findViewById(R.id.tv_joke)

        // Find the progress bar and assign
        // it to the variable.
        progressBar1 = findViewById(R.id.idLoadingPB1)
        progressBar2 = findViewById(R.id.idLoadingPB2)

        lv_categories = findViewById<ListView>(R.id.categories)

        // Set an OnClickListener on the button view.
        btn_jokes.setOnClickListener {
            // show the progress bar
            progressBar1.visibility = View.VISIBLE

            // Call the getjokes() method of the ApiCall class,
            // passing a callback function as a parameter.
            APiCall().getjokes(this) { jokes ->
                // Set the text of the text view to the
                // joke value returned by the API response.
                tv_jokes.text = jokes.value
                // hide the progress bar
                progressBar1.visibility = View.GONE
            }
        }

        lv_categories.setOnItemClickListener { adapterView, view, position, id ->
            val category = adapterView.getItemAtPosition(position) as String
            fetchRandomJoke(category)
        }

        fetchCategories()
    }

    private fun fetchRandomJoke(category: String) {
        progressBar1.visibility = View.VISIBLE

            APiCall().getjokes(this, category) { joke ->
                runOnUiThread {
                    tv_jokes.text = joke.value // Display the joke
                    progressBar1.visibility = View.GONE // Hide progress bar
                }
            }
    }

    private fun fetchCategories() {
        progressBar2.visibility = View.VISIBLE // Show progress bar while loading

        APiCall().getCategories(this) { categories ->
            val sortedCategories = categories.sorted()

            runOnUiThread {
                val categoriesAdapter = ArrayAdapter(
                    this, // Context
                    android.R.layout.simple_list_item_1, // Layout for the row
                    sortedCategories // Sorted Data
                )
                lv_categories.adapter = categoriesAdapter
                progressBar2.visibility = View.GONE // Hide progress bar after loading categories
            }
        }
    }
}