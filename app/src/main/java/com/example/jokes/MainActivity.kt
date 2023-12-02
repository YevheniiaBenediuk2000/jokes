package com.example.jokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var btn_jokes: Button
    private lateinit var tv_jokes: TextView
    private lateinit var progressBar: ProgressBar

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
        // it to the varriable.
        progressBar = findViewById(R.id.idLoadingPB)

        // Set an OnClickListener on the button view.
        btn_jokes.setOnClickListener {
            // show the progress bar
            progressBar.visibility = View.VISIBLE

            // Call the getjokes() method of the ApiCall class,
            // passing a callback function as a parameter.
            APiCall().getjokes(this) { jokes ->
                // Set the text of the text view to the
                // joke value returned by the API response.
                tv_jokes.text = jokes.value
                // hide the progress bar
                progressBar.visibility = View.GONE
            }
        }
    }
}