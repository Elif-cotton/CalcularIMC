package com.example.calcular9.model

data class Paciente(
    val name: String,
    val age: String = "",
    val weight: String = "",
    val height: String = "",
    val bmiResult: String = "0.0",
    val gender: String = "Hombre"
)