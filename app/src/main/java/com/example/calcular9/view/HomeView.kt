package com.example.calcular9.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val state by viewModel.state.observeAsState(IMCState())
    val errorMessage by viewModel.errorMessage.observeAsState()
    var showNotification by remember { mutableStateOf(errorMessage != null) }

    // Watch for changes in errorMessage to update showNotification
    LaunchedEffect(errorMessage) {
        showNotification = errorMessage != null
    }

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

        if (showNotification) {
            NotificationBanner(
                message = errorMessage ?: "",
                onDismiss = { showNotification = false }
            )
        }

        Text(
            text = state.bmiResult,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun NotificationBanner(
    message: String,
    onDismiss: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onDismiss),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Green)
        ) {
            Text(
                text = "¡Cuidado!",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message,
                color = Color.White,
                fontSize = 25.sp,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onDismiss,
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {
                Text(
                    text = "Comprendido",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


