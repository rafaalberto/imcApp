package br.com.imcapp

import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.imcapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val DIVISOR = 100
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar()
        setViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.system_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_clear -> {
                clearFields()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("WrongConstant")
    private fun setSupportActionBar() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)
    }

    private fun setViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.resultText.text = viewModel.bmi.toString()
        binding.calculateButton.setOnClickListener {
            calculateBMI(viewModel)
        }
    }

    private fun calculateBMI(viewModel: MainActivityViewModel) {
        if (validFields()) {
            try {
                val weight = binding.weightEditText.text.toString().toDouble()
                val height = binding.heightEditText.text.toString().toDouble()
                viewModel.calculateBMI(weight, height / DIVISOR)
                binding.resultText.text = viewModel.bmi.toString()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, R.string.validate_wrong_patter, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validFields(): Boolean {
        if (binding.weightEditText.text == null || binding.weightEditText.text.isNullOrBlank()) {
            Toast.makeText(this, R.string.validate_type_weight, Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.heightEditText.text == null || binding.heightEditText.text.isNullOrBlank()) {
            Toast.makeText(this, R.string.validate_type_height, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun clearFields() {
        binding.weightEditText.text?.clear()
        binding.heightEditText.text?.clear()
        binding.resultText.text = ""
        binding.weightEditText.requestFocus()
    }
}
