package ismael.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    private var operator1: Double = 0.0
    private var operator2: Double = 0.0

    private var alreadyDot: Boolean = false
    private var lastOperation: String = ""
    private var isResult: Boolean = false
    private var lastNumber: Boolean = false
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.input)

        val btClr: Button = findViewById(R.id.CLR)
        btClr.setOnClickListener{
            onClr()
        }

    }

    private fun onClr(){
        tvInput?.text = ""
        operator1 = 0.0
        operator2 = 0.0
        lastOperation = ""
        isResult = false
        alreadyDot = false
        lastNumber = false
    }

    fun onNumber(view: View){
        tvInput?.let{
            if(isResult){
                onClr()
            }
            if((tvInput as TextView).text.length <= 30){
                tvInput!!.append((view as Button).text)
                lastNumber = true

            }
        }
    }
    fun onDot(view: View){
        tvInput?.let{
            if(!alreadyDot && lastNumber){
                tvInput!!.append(".")
                alreadyDot = true
                lastNumber = false
            }
        }
    }

    fun onOp(view: View){
        tvInput?.let{
            isResult = false
            lastNumber = false
            alreadyDot = false
            if(tvInput!!.text != "" && "Infinity" !in tvInput!!.text ){
                if (lastOperation == ""){
                    lastOperation = (view as Button).text.toString()
                    operator1 = tvInput!!.text.toString().toDouble()
                    tvInput!!.append(lastOperation)
                } else if (tvInput!!.text[tvInput!!.text.length - 1].toString() !in arrayListOf("+", "-", "*", "/")){
                    onEqual(null)
                    isResult = false
                    lastOperation = (view as Button).text.toString()
                    tvInput!!.append(lastOperation)
                } else {
                    TODO("IMPLEMENT OPERATION CHANGE")
                    Log.d("PANIC", "PANICASJICASIJ")
                }
            }
        }
    }

    fun onEqual(view: View?){
        var result = 0.0
        if (tvInput!!.text != "" && !isResult){
            if (tvInput!!.text[tvInput!!.text.length - 1].toString() !in arrayListOf("+", "-", "x", "/")){
                val aux = tvInput!!.text.toString().split("+","-","/","x")
                if (aux.size > 1){
                    operator2 = aux[1].toDouble()
                }
            }
            if(operator2 == 0.0){
                onClr()
                tvInput!!.text = "Infinity"
                isResult = true
            } else {
                if(!isResult){
                    when(lastOperation) {
                        "+" -> result = operator1 + operator2
                        "-" -> result = operator1 - operator2
                        "x" -> result = operator1 * operator2
                        "/" -> result = operator1 / operator2
                    }

                    operator1 = result
                    operator2 = 0.0
                    tvInput!!.text = intShow(result)
                    lastOperation = ""
                    isResult = true
                }
            }

        }
    }
    private fun intShow(number: Double): String {
        val decimal = number.toBigDecimal().toPlainString().split(".")
        if (decimal.size > 1){
            val decimalString: String = decimal[1]
            val decimalInt: Int? = decimalString.toIntOrNull()
            decimalInt?.let{
                if (decimalInt == 0){
                    return decimal[0]
                } else {
                    val df = DecimalFormat("#.########")
                    var stNumber = df.format(number)
                    repeat(14){
                        stNumber = stNumber.removeSuffix("0")
                    }
                    return stNumber
                }
            }
        }
        TODO("VER DIVISAO")
    }

}

