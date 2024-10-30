package com.example.mycalculate

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var op1: Int = 0
    var res: Int = 0
    var tmp: Int = 0
    private lateinit var textInput: EditText
    private lateinit var textResult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //nut chuyen doi
        textInput = findViewById(R.id.textInput)
        textResult = findViewById(R.id.textResult)

        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)
        findViewById<Button>(R.id.btnequal).setOnClickListener(this)
        findViewById<Button>(R.id.button22).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        when (id) {
            R.id.btn0 -> addDigit(0)
            R.id.btn1 -> addDigit(1)
            R.id.btn2 -> addDigit(2)
            R.id.btn3 -> addDigit(3)
            R.id.btn4 -> addDigit(4)
            R.id.btn5 -> addDigit(5)
            R.id.btn6 -> addDigit(6)
            R.id.btn7 -> addDigit(7)
            R.id.btn8 -> addDigit(8)
            R.id.btn9 -> addDigit(9)
            R.id.btnequal -> convert()
            R.id.button22 -> clear()
        }
    }

    fun addDigit(c: Int) {
        op1 = op1 * 10 + c
        tmp = op1
        textInput.setText(op1.toString()+"$")
    }
    fun convert(){
        res = tmp.toString().toInt() * 23000
        textResult.text = String.format("%.2f", res.toDouble())+"vnd"
    }
    fun clear(){
        textInput.setText("")
        textResult.setText("")
        op1 = 0
    }
}
