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

        // Botões de números
        findViewById<Button>(R.id.button0).setOnClickListener { appendNumber("0") }
        findViewById<Button>(R.id.button1).setOnClickListener { appendNumber("1") }
        findViewById<Button>(R.id.button2).setOnClickListener { appendNumber("2") }
        findViewById<Button>(R.id.button3).setOnClickListener { appendNumber("3") }
        findViewById<Button>(R.id.button4).setOnClickListener { appendNumber("4") }
        findViewById<Button>(R.id.button5).setOnClickListener { appendNumber("5") }
        findViewById<Button>(R.id.button6).setOnClickListener { appendNumber("6") }
        findViewById<Button>(R.id.button7).setOnClickListener { appendNumber("7") }
        findViewById<Button>(R.id.button8).setOnClickListener { appendNumber("8") }
        findViewById<Button>(R.id.button9).setOnClickListener { appendNumber("9") }
        findViewById<Button>(R.id.buttonDecimal).setOnClickListener { appendNumber(".") }

        // Botões de operações
        findViewById<Button>(R.id.buttonAdd).setOnClickListener { setOperation("+") }
        findViewById<Button>(R.id.buttonSubtract).setOnClickListener { setOperation("−") }
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener { setOperation("×") }
        findViewById<Button>(R.id.buttonDivide).setOnClickListener { setOperation("÷") }

        // Botões de funcionalidades
        findViewById<Button>(R.id.buttonClear).setOnClickListener { clear() }
        findViewById<Button>(R.id.buttonSign).setOnClickListener { changeSign() }
        findViewById<Button>(R.id.buttonPercent).setOnClickListener { calculatePercentage() }
        findViewById<Button>(R.id.buttonEquals).setOnClickListener { calculateResult() }
    }

    private fun appendNumber(number: String) {
        if (number == "." && currentNumber.contains(".")) return // Evita múltiplos pontos decimais
        currentNumber += number
        displayTextView.text = currentNumber
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

    private fun calculateResult() {
        if (previousNumber.isNotEmpty() && currentNumber.isNotEmpty()) {
            val num1 = previousNumber.toDouble()
            val num2 = currentNumber.toDouble()
            val result = when (operation) {
                "+" -> num1 + num2
                "−" -> num1 - num2
                "×" -> num1 * num2
                "÷" -> if (num2 != 0.0) num1 / num2 else Double.NaN
                else -> 0.0
            }
            displayTextView.text = result.toString()
            previousNumber = result.toString()
            currentNumber = ""
        }
    }
}