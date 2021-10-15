package com.rizwanamjadnov.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var monitorTextView: TextView
    private lateinit var zeroButton: Button
    private lateinit var oneButton: Button
    private lateinit var twoButton: Button
    private lateinit var threeButton: Button
    private lateinit var fourButton: Button
    private lateinit var fiveButton: Button
    private lateinit var sixButton: Button
    private lateinit var sevenButton: Button
    private lateinit var eightButton: Button
    private lateinit var nineButton: Button
    private lateinit var periodButton: Button

    private lateinit var clearButton: Button
    private lateinit var backspaceButton: Button

    private lateinit var plusButton: Button
    private lateinit var minusButton: Button
    private lateinit var multiplyButton: Button
    private lateinit var divideButton: Button

    private lateinit var equalsButton: Button

    private var leftOperand:String = ""
    private var rightOperand:String = ""
    private var operator: Char? = null

    private fun initializeItems(){
        monitorTextView = findViewById(R.id.monitor)
        zeroButton = findViewById(R.id.zero)
        oneButton = findViewById(R.id.one)
        twoButton = findViewById(R.id.two)
        threeButton = findViewById(R.id.three)
        fourButton = findViewById(R.id.four)
        fiveButton = findViewById(R.id.five)
        sixButton = findViewById(R.id.six)
        sevenButton = findViewById(R.id.seven)
        eightButton = findViewById(R.id.eight)
        nineButton = findViewById(R.id.nine)
        periodButton = findViewById(R.id.period)

        clearButton = findViewById(R.id.clear)
        backspaceButton = findViewById(R.id.backspace)

        plusButton = findViewById(R.id.plus)
        minusButton = findViewById(R.id.minus)
        multiplyButton = findViewById(R.id.multiply)
        divideButton = findViewById(R.id.divide)

        equalsButton = findViewById(R.id.equals)

    }

    private fun attachListners() {
        zeroButton.setOnClickListener {
            monitorTextView.append("0")
        }
        oneButton.setOnClickListener {
            monitorTextView.append("1")
        }
        twoButton.setOnClickListener{
            monitorTextView.append("2")
        }
        threeButton.setOnClickListener{
            monitorTextView.append("3")
        }
        fourButton.setOnClickListener{
            monitorTextView.append("4")
        }
        fiveButton.setOnClickListener{
            monitorTextView.append("5")
        }
        sixButton.setOnClickListener{
            monitorTextView.append("6")
        }
        sevenButton.setOnClickListener{
            monitorTextView.append("7")
        }
        eightButton.setOnClickListener{
            monitorTextView.append("8")
        }
        nineButton.setOnClickListener{
            monitorTextView.append("9")
        }
        periodButton.setOnClickListener{
            monitorTextView.append(".")
        }

        clearButton.setOnClickListener{
            monitorTextView.text = ""
        }
        backspaceButton.setOnClickListener{
            monitorTextView.apply {
                text = text.subSequence(0, text.length-1)
            }
        }

        plusButton.setOnClickListener{
            monitorTextView.append("+")
        }

        minusButton.setOnClickListener{
            monitorTextView.append("-")
        }

        multiplyButton.setOnClickListener{
            monitorTextView.append("x")
        }

        divideButton.setOnClickListener{
            monitorTextView.append("/")
        }

        equalsButton.setOnClickListener{
            try{
                evaluateResult()
            }
            catch (ex: Exception){
                monitorTextView.text = getString(R.string.math_error)
            }
        }

    }

    private fun evaluateResult(){
        val expression:String = monitorTextView.text.toString()+'+'
        for (character in expression){
            if(character in charArrayOf('+', '-', 'x', '/')){
                    if(leftOperand=="") {
                        leftOperand = rightOperand
                        rightOperand = ""
                        operator = character
                        continue
                    }
                    else {
                        when (operator) {
                            '+' -> {
                                leftOperand =
                                    (leftOperand!!.toDouble() + rightOperand!!.toDouble()).toString()
                            }
                            '-' -> {
                                leftOperand =
                                    (leftOperand!!.toDouble() - rightOperand!!.toDouble()).toString()
                            }
                            'x' -> {
                                leftOperand =
                                    (leftOperand!!.toDouble() * rightOperand!!.toDouble()).toString()
                            }
                            '/' -> {
                                leftOperand =
                                    (leftOperand!!.toDouble() / rightOperand!!.toDouble()).toString()
                            }
                        }
                        rightOperand = ""
                        operator = character
                    }
            }
            else{
                rightOperand+=character
            }
        }
        monitorTextView.text = leftOperand
        leftOperand = ""
        rightOperand = ""
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeItems()
        attachListners()
    }

}