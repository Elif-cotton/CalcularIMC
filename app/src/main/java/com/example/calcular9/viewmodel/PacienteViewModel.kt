package com.example.calcular9.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.calcular9.model.Paciente

class PacienteViewModel : ViewModel() {
    private val _pacientes = mutableStateListOf<Paciente>()
    val pacientes: List<Paciente> get() = _pacientes

    fun agregarPaciente(nombre: String) {
        if (nombre.isNotBlank()) {
            _pacientes.add(Paciente(nombre))
        }
    }
}