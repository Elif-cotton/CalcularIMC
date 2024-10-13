package com.example.calcular9.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calcular9.components.OutlinedTextInput
import com.example.calcular9.viewmodel.IMCViewModel
import com.example.calcular9.components.GenderSelectionSegmentedButtonRow
import java.util.Locale

@Composable
fun HomeView(viewModel: IMCViewModel = IMCViewModel()) {
    var age by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var bmiResult by remember { mutableStateOf("0.0") } // Estado inicial para el IMC con valor 0.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Centrar elementos horizontalmente
    ) {
        Text(
            text = "Calculadora IMC",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center // Centrar el texto
        )

        Spacer(modifier = Modifier.height(24.dp))

        GenderSelectionSegmentedButtonRow()

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextInput(value = age, onValueChange = { age = it }, label = "Edad")
        OutlinedTextInput(value = weight, onValueChange = { weight = it }, label = "Peso (kg)")
        OutlinedTextInput(value = height, onValueChange = { height = it }, label = "Altura (cm)")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val imc = viewModel.calculateBMI(weight, height)
                // Formatear el resultado del IMC con Locale.US para evitar advertencias de configuración regional
                bmiResult = if (imc > 0) String.format(Locale.US, "%.1f", imc) else "Datos inválidos"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calcular", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Text centrado para mostrar el resultado del IMC
        Text(
            text = bmiResult,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

