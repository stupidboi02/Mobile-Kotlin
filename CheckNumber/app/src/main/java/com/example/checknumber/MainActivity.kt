package com.example.checknumber

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var editTextInput: EditText
    lateinit var radioOptions: RadioGroup
    lateinit var buttonShow : Button
    lateinit var textViewResult: TextView
    lateinit var listViewResult: ListView
    val numberList = mutableListOf<Int>()
    lateinit var adapter: ArrayAdapter<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //
        editTextInput = findViewById(R.id.editTextInput)
        radioOptions = findViewById(R.id.radioOptions)
        buttonShow = findViewById(R.id.buttonShow)
        textViewResult = findViewById(R.id.textViewResult)
        listViewResult = findViewById(R.id.listViewResult)
        //adapter cho listview
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numberList)
        listViewResult.adapter = adapter

        buttonShow.setOnClickListener {
            val input = editTextInput.text.toString().toIntOrNull()
            if (input == null) {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            numberList.clear()
            when (radioOptions.checkedRadioButtonId) {
                R.id.radio_c -> numberList.addAll(generateEvenNumbers(input))
                R.id.radio_l -> numberList.addAll(generateOddNumbers(input))
                R.id.radio_cp -> numberList.addAll(generatePerfectSquares(input))
            }
            adapter.notifyDataSetChanged()
            textViewResult.text = "Found ${numberList.size} results"
        }
    }
    private fun generateEvenNumbers(limit: Int): List<Int> {
        return (1..limit).filter { it % 2 == 0 }
    }

    private fun generateOddNumbers(limit: Int): List<Int> {
        return (1..limit).filter { it % 2 != 0 }
    }

    private fun generatePerfectSquares(limit: Int): List<Int> {
        return (1..limit).filter { isPerfectSquare(it) }
    }

    private fun isPerfectSquare(n: Int): Boolean {
        val sqrt = Math.sqrt(n.toDouble()).toInt()
        return sqrt * sqrt == n
    }
}