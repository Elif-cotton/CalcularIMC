package com.example.calcular9.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import com.example.calcular9.ui.theme.Purple40


@Composable
fun GenderSelectionSegmentedButtonRow() {
    // Lista de opciones y el estado de selección
    val options = listOf("Hombre", "Mujer")
    val selectedOption = remember { mutableStateOf(options[0]) }

    // Usar MultiChoiceSegmentedButtonRow para mostrar los botones
    MultiChoiceSegmentedButtonRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        options.forEach { option ->
            SegmentedButton(
                checked = selectedOption.value == option,
                onCheckedChange = { isChecked ->
                    if (isChecked) {
                        selectedOption.value = option
                    }
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.weight(1f),
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = Purple40, // Color cuando el botón está seleccionado
                    inactiveContainerColor = Color.White, // Color cuando el botón no está seleccionado
                    activeContentColor = Color.White, // Color del texto cuando el botón está seleccionado
                    inactiveContentColor = MaterialTheme.colorScheme.onSurface // Color del texto cuando el botón no está seleccionado
                )
            ) {
                Text(
                    text = option,
                    fontSize = 16.sp
                )
            }
        }
    }
}