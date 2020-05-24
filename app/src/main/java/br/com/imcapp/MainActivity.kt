package br.com.imcapp

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var textInputWeight: EditText
    private lateinit var textInputHeight: EditText
    private lateinit var textViewResult: TextView
    private lateinit var buttonCalculate: Button

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)
        loadComponents()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.system_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_clear -> {
                clearFields()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun loadComponents() {
        textInputWeight = findViewById(R.id.textInputWeight)
        textInputHeight = findViewById(R.id.textInputHeight)
        textViewResult = findViewById(R.id.textViewResult)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        buttonCalculate.setOnClickListener { calculateBMI() }
    }

    private fun calculateBMI() {
        val weight: Double = this.textInputWeight.text.toString().toDouble()
        val height : Double = textInputHeight.text.toString().toDouble() / 100
        val bmi : Double = weight / (height * height)
        textViewResult.text = "IMC: $bmi"
    }

    private fun clearFields() {
        textInputWeight.text.clear()
        textInputHeight.text.clear()
        textViewResult.text = ""
        textInputWeight.requestFocus()
        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(textInputWeight, InputMethodManager.SHOW_IMPLICIT)
    }
}
