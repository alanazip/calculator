package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var displayTextView: TextView
    private var currentNumber = ""
    private var operation = ""
    private var previousNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayTextView = findViewById(R.id.displayTextView)

        findViewById<Button>(R.id.buttonClear).setOnClickListener { clear() }
        findViewById<Button>(R.id.buttonSign).setOnClickListener { changeSign() }
        findViewById<Button>(R.id.buttonPercent).setOnClickListener { calculatePercentage() }
        findViewById<Button>(R.id.buttonDivide).setOnClickListener { setOperation("÷") }
    }

    private fun clear() {
        currentNumber = ""
        previousNumber = ""
        operation = ""
        displayTextView.text = "0"
    }

    private fun changeSign() {
        if (currentNumber.isNotEmpty()) {
            currentNumber = if (currentNumber.startsWith("-")) {
                currentNumber.substring(1)
            } else {
                "-$currentNumber"
            }
            displayTextView.text = currentNumber
        }
    }

    private fun calculatePercentage() {
        if (currentNumber.isNotEmpty()) {
            val num = currentNumber.toDouble() / 100
            currentNumber = num.toString()
            displayTextView.text = currentNumber
        }
    }

    private fun setOperation(op: String) {
        if (currentNumber.isNotEmpty()) {
            previousNumber = currentNumber
            currentNumber = ""
            operation = op
            displayTextView.text = op
        }
    }

    // Continue com outros métodos de operações e lógica da calculadora...

}