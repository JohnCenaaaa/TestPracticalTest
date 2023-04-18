package com.example.testpracticaltest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner : Spinner = findViewById(R.id.List)
        spinner.onItemSelectedListener = this

        ArrayAdapter.createFromResource(
            this,
            R.array.select,
            android.R.layout.simple_spinner_item
        ). also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        val gradeButton : Button = findViewById(R.id.Submit)
        gradeButton.setOnClickListener{
            val score : EditText = findViewById(R.id.Score)

            val result = when{
                score.text.toString().toDouble() >= 80 -> "A"
                score.text.toString().toDouble() >= 75 -> "A-"
                score.text.toString().toDouble() >= 70 -> "B+"
                score.text.toString().toDouble() >= 65 -> "B"
                score.text.toString().toDouble() >= 60 -> "B-"
                score.text.toString().toDouble() >= 55 -> "C+"
                score.text.toString().toDouble() >= 50 -> "C"
                else -> "D"
            }
            val resultText : TextView = findViewById(R.id.Result)
            resultText.text = "Result: " + " " + result
        }
            findViewById<Button>(R.id.Contact).setOnClickListener{
                startActivity(email())
            }
    }
    private fun email(): Intent? {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("aaa@gmail.com"))
        emailIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Name: ${findViewById<EditText>(R.id.editTextText).text}\n"
                    +"Course: ${findViewById<TextView>(R.id.Selection).text}\n"
                    +"Score: ${findViewById<EditText>(R.id.Score).text}\n"
                    +"Grade: ${findViewById<TextView>(R.id.Result).text}"
        )
        return emailIntent
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectionTv : TextView = findViewById(R.id.Selection)
        selectionTv.text = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}