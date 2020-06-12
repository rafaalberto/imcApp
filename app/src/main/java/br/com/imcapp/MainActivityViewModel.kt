package br.com.imcapp

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var bmi = 0.0

    fun calculateBMI(weight: Double, height: Double) {
        bmi = weight / (height * height)
    }
}