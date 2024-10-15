package com.example.calcular9.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calcular9.model.Paciente


class HomeViewModel : ViewModel() {
    private val _state = MutableLiveData(Paciente(name = ""))
    val state: LiveData<Paciente> get() = _state

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun onAgeChange(newAge: String) {
        _state.value = _state.value?.copy(age = newAge.toIntOrNull()?.toString() ?: "")
    }

    fun onWeightChange(newWeight: String) {
        _state.value = _state.value?.copy(weight = newWeight.toDoubleOrNull()?.toString() ?: "")
    }

    fun onHeightChange(newHeight: String) {
        _state.value = _state.value?.copy(height = newHeight.toDoubleOrNull()?.toString() ?: "")
    }

    fun onGenderChange(newGender: String) {
        _state.value = _state.value?.copy(gender = newGender)
    }

    fun calculateBMI() {
        val weightInKg = _state.value?.weight?.toDoubleOrNull() ?: 0.0
        val heightInMeters = (_state.value?.height?.toDoubleOrNull() ?: 0.0) / 100

        if (weightInKg <= 0.0 || heightInMeters <= 0.0) {
            _errorMessage.value = "No olvides llenar todos los campos con los datos correctos."
            _state.value = _state.value?.copy(bmiResult = "0.0")
            return
        }

        val imc = weightInKg / (heightInMeters * heightInMeters)
        _state.value = _state.value?.copy(bmiResult = String.format("%.1f", imc))
        _errorMessage.value = null
    }

    fun calcularEstadoSalud(imc: Double): String {
        return when {
            imc < 18.5 -> "Bajo peso"
            imc in 18.5..24.9 -> "Peso Normal"
            imc in 25.0..29.9 -> "Sobrepeso"
            imc in 30.0..34.9 -> "Obesidad I"
            imc in 35.0..39.9 -> "Obesidad II"
            else -> "Obesidad III"
        }
    }

    fun guardarDatosPaciente(nombre: String) {

    }
}