package ismael.example.mycalculator

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.DecimalFormat

class CalculatorController(textOutput: TextView) {
    private var operator1: Double = 0.0
    private var operator2: Double = 0.0
    private var alreadyDot: Boolean = false
    private var lastOperation: String = ""
    private var isResult: Boolean = false
    private var lastNumber: Boolean = false
    private val textView: TextView = textOutput
    private var output: String = ""
        get() = field
        set(value) {
            field = value
            textView.text = value
        }

    fun onClr() {
        output = ""
        operator1 = 0.0
        operator2 = 0.0
        lastOperation = ""
        isResult = false
        alreadyDot = false
        lastNumber = false
    }

    fun onNumber(view: View) {
        if (isResult) {
            onClr()
        }
        if (output.length <= 30) {
            output += ((view as Button).text)
            lastNumber = true
        }
    }

    fun onDot(view: View) {
        if (!alreadyDot && lastNumber) {
            output += "."
            alreadyDot = true
            lastNumber = false
        }
    }

    fun onOp(view: View) {
        isResult = false
        lastNumber = false
        alreadyDot = false
        if (output != "" && "Infinity" !in output) {
            if (lastOperation == "") {
                lastOperation = (view as Button).text.toString()
                operator1 = output.toDouble()
                output += lastOperation
            } else if (output[output.length - 1].toString() !in arrayListOf(
                    "+",
                    "-",
                    "*",
                    "/"
                )
            ) {
                onEqual(null)
                isResult = false
                lastOperation = (view as Button).text.toString()
                output += lastOperation
            } else {
                TODO("IMPLEMENT OPERATION CHANGE")
                Log.d("PANIC", "PANICASJICASIJ")
            }
        }
    }

    fun onEqual(view: View?) {
        var result = 0.0
        if (output != "" && !isResult) {
            if (output[output.length - 1].toString() !in arrayListOf(
                    "+",
                    "-",
                    "x",
                    "/"
                )
            ) {
                val aux = output.toString().split("+", "-", "/", "x")
                if (aux.size > 1) {
                    operator2 = aux[1].toDouble()
                }
            }
            if (operator2 == 0.0) {
                onClr()
                output = "Infinity"
                isResult = true
            } else {
                if (!isResult) {
                    when (lastOperation) {
                        "+" -> result = operator1 + operator2
                        "-" -> result = operator1 - operator2
                        "x" -> result = operator1 * operator2
                        "/" -> result = operator1 / operator2
                    }

                    operator1 = result
                    operator2 = 0.0
                    output = intShow(result)
                    lastOperation = ""
                    isResult = true
                }
            }

        }
    }

    private fun intShow(number: Double): String {
        val decimal = number.toBigDecimal().toPlainString().split(".")
        if (decimal.size > 1) {
            val decimalString: String = decimal[1]
            val decimalInt: Int? = decimalString.toIntOrNull()
            decimalInt?.let {
                if (decimalInt == 0) {
                    return decimal[0]
                } else {
                    val df = DecimalFormat("#.########")
                    var stNumber = df.format(number)
                    repeat(14) {
                        stNumber = stNumber.removeSuffix("0")
                    }
                    return stNumber
                }
            }
        }
        TODO("VER DIVISAO")
    }

    public fun onClickEvents(button: Button) {
        when (button.id) {
            R.id.CLR -> {
                button.setOnClickListener {
                    onClr()
                }
            }
            R.id.add -> {
                button.setOnClickListener {
                    onOp(button)
                }
            }
            R.id.minus -> {
                button.setOnClickListener {
                    onOp(button)
                }
            }
            R.id.multiply -> {
                button.setOnClickListener {
                    onOp(button)
                }
            }
            R.id.division -> {
                button.setOnClickListener {
                    onOp(button)
                }
            }
            R.id.equal -> {
                button.setOnClickListener {
                    onEqual(button)
                }
            }
            R.id.dot -> {
                button.setOnClickListener {
                    onDot(button)
                }
            }
            else -> {
                button.setOnClickListener {
                    onNumber(button)
                }
            }
        }
    }
}