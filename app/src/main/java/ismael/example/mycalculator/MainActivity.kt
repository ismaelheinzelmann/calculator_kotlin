package ismael.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.input)

        val myCalculator = CalculatorController(tvInput!!)
        val buttonsIds: List<String> = listOf(
            "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine", "zero", "equal", "add", "minus", "multiply", "division", "CLR", "dot"
        )
        for (buttonId in buttonsIds){
            val id: Int = resources.getIdentifier(buttonId, "id", packageName)
            val button: Button = findViewById(id)
            myCalculator.onClickEvents(button)
        }
    }
}

