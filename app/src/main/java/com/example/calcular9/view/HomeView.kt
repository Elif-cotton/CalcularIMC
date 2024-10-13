package com.example.calcular9.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calcular9.components.OutlinedTextInput
import com.example.calcular9.viewmodel.IMCViewModel
import com.example.calcular9.components.GenderSelectionSegmentedButtonRow

@Composable
fun HomeView(viewModel: IMCViewModel = IMCViewModel()) {
    var age by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Calculadora IMC",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
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
                //logica de calculo
                val imc = viewModel.calculateBMI(weight, height)
                // Mostrar el resultado de IMC
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calcular", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

