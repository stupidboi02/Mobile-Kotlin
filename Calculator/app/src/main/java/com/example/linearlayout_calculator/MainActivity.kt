    package com.example.linearlayout_calculator

    import android.os.Bundle
    import android.widget.Button
    import android.view.View
    import android.widget.TextView
    import androidx.appcompat.app.AppCompatActivity

    class MainActivity : AppCompatActivity(), View.OnClickListener {

        lateinit var textResult: TextView
        var state: Int = 1
        var op: Int = 0
        var op1: Int = 0
        var op2: Int = 0

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            textResult = findViewById(R.id.result)

            findViewById<Button>(R.id.zero).setOnClickListener(this)
            findViewById<Button>(R.id.one).setOnClickListener(this)
            findViewById<Button>(R.id.two).setOnClickListener(this)
            findViewById<Button>(R.id.three).setOnClickListener(this)
            findViewById<Button>(R.id.four).setOnClickListener(this)
            findViewById<Button>(R.id.five).setOnClickListener(this)
            findViewById<Button>(R.id.six).setOnClickListener(this)
            findViewById<Button>(R.id.seven).setOnClickListener(this)
            findViewById<Button>(R.id.eight).setOnClickListener(this)
            findViewById<Button>(R.id.nine).setOnClickListener(this)

            findViewById<Button>(R.id.add).setOnClickListener(this)
            findViewById<Button>(R.id.subtract).setOnClickListener(this)
            findViewById<Button>(R.id.multiply).setOnClickListener(this)
            findViewById<Button>(R.id.divide).setOnClickListener(this)
            findViewById<Button>(R.id.equal).setOnClickListener(this)
            findViewById<Button>(R.id.clear).setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val id = p0?.id

            when (id) {
                R.id.zero -> addDigit(0)
                R.id.one -> addDigit(1)
                R.id.two -> addDigit(2)
                R.id.three -> addDigit(3)
                R.id.four -> addDigit(4)
                R.id.five -> addDigit(5)
                R.id.six -> addDigit(6)
                R.id.seven -> addDigit(7)
                R.id.eight -> addDigit(8)
                R.id.nine -> addDigit(9)
            }

            when (id) {
                R.id.add -> {
                    op = 1
                    state = 2
                }
                R.id.subtract -> {
                    op = 2
                    state = 2
                }
                R.id.multiply -> {
                    op = 3
                    state = 2
                }
                R.id.divide -> {
                    op = 4
                    state = 2
                }
                R.id.equal -> {
                    calculateResult()
                }
                R.id.clear -> {
                    resetCalculator()
                }
            }
        }

        fun addDigit(c: Int) {
            if (state == 1) {
                op1 = op1 * 10 + c
                textResult.text = "$op1"
            } else {
                op2 = op2 * 10 + c
                textResult.text = "$op2"
            }
        }

        fun calculateResult() {
            var result = 0

            when (op) {
                0 -> result = 0
                1 -> result = op1 + op2
                2 -> result = op1 - op2
                3 -> result = op1 * op2
                4 -> if (op2 != 0) result = op1 / op2 else textResult.text = "Error"
            }

            textResult.text = "$result"
            resetCalculator()
        }

        fun resetCalculator() {
            state = 1
            op1 = 0
            op2 = 0
            op = 0
            textResult.text = "0"
        }
    }
}
