package com.example.calcular9.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calcular9.components.GenderSelectionSegmentedButtonRow

import com.example.calcular9.components.OutlinedTextInput
import com.example.calcular9.viewmodel.IMCViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calcular9.model.IMCState

@Composable
fun HomeView(viewModel: IMCViewModel = viewModel()) {
    // Observa el estado del ViewModel
    val state by viewModel.state.observeAsState(IMCState())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora IMC",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Pasa las funciones y el estado necesario al componente
        GenderSelectionSegmentedButtonRow(
            selectedOption = state.gender,
            onOptionSelected = { viewModel.onGenderChange(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextInput(
            value = state.age,
            onValueChange = { viewModel.onAgeChange(it) },
            label = "Edad"
        )
        OutlinedTextInput(
            value = state.weight,
            onValueChange = { viewModel.onWeightChange(it) },
            label = "Peso (kg)"
        )
        OutlinedTextInput(
            value = state.height,
            onValueChange = { viewModel.onHeightChange(it) },
            label = "Altura (cm)"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.calculateBMI() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calcular", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = state.bmiResult,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}


