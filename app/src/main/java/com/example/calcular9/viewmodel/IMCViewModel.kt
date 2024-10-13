package com.example.calcular9.viewmodel

import androidx.lifecycle.ViewModel

class IMCViewModel : ViewModel() {

    fun calculateBMI(weight: String, height: String): Double {
        val weightInKg = weight.toDoubleOrNull() ?: return 0.0
        val heightInCm = height.toDoubleOrNull() ?: return 0.0
        val heightInMeters = heightInCm / 100
        return weightInKg / (heightInMeters * heightInMeters)
    }
}