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
fun GenderSelectionSegmentedButtonRow(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val options = listOf("Hombre", "Mujer")

    MultiChoiceSegmentedButtonRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        options.forEach { option ->
            SegmentedButton(
                checked = selectedOption == option,
                onCheckedChange = { isChecked ->
                    if (isChecked) {
                        onOptionSelected(option)
                    }
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.weight(1f),
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = Purple40,
                    inactiveContainerColor = Color.White,
                    activeContentColor = Color.White,
                    inactiveContentColor = MaterialTheme.colorScheme.onSurface
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