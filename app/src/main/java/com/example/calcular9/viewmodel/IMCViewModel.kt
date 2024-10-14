package com.example.calcular9.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calcular9.model.IMCState
import java.util.Locale


class IMCViewModel : ViewModel() {
    private val _state = MutableLiveData(IMCState())
    val state: LiveData<IMCState> get() = _state

    fun onAgeChange(newAge: String) {
        _state.value = _state.value?.copy(age = newAge)
    }

    fun onWeightChange(newWeight: String) {
        _state.value = _state.value?.copy(weight = newWeight)
    }

    fun onHeightChange(newHeight: String) {
        _state.value = _state.value?.copy(height = newHeight)
    }

    fun onGenderChange(newGender: String) {
        _state.value = _state.value?.copy(gender = newGender)
    }

    fun calculateBMI() {
        val weightInKg = _state.value?.weight?.toDoubleOrNull() ?: return
        val heightInMeters = (_state.value?.height?.toDoubleOrNull() ?: return) / 100
        val imc = if (heightInMeters > 0) weightInKg / (heightInMeters * heightInMeters) else 0.0
        _state.value = _state.value?.copy(bmiResult = String.format(Locale.US, "%.1f", imc))
    }
}