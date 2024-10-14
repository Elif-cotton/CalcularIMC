package com.example.calcular9.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults


import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.calcular9.navigate.Screen
import com.example.calcular9.viewmodel.PacienteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientListView(
    navController: NavHostController,
    viewModel: PacienteViewModel = PacienteViewModel()
) {
    val pacientes by remember { mutableStateOf(viewModel.pacientes) }
    var showAddPatientDialog by remember { mutableStateOf(false) }

    // Definimos 'nombre' aquí para que esté accesible
    var nombre by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Pacientes", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6A1B9A) // Morado fuerte
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddPatientDialog = true },
                containerColor = Color(0xFF6A1B9A) // Morado fuerte
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar Paciente",
                    tint = Color.White, // Icono blanco
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                LazyColumn {
                    items(pacientes) { paciente ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Mostrar "Nombre: paciente.nombre"
                                Text(
                                    text = "Nombre: ${paciente.name}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                // Botón negro con letras blancas en negrita
                                Button(
                                    onClick = { navController.navigate(Screen.Home.route) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black // Fondo negro
                                    )
                                ) {
                                    Text(
                                        text = "Calcular IMC",
                                        color = Color.White, // Texto blanco
                                        fontWeight = FontWeight.Bold // Texto en negrita
                                    )
                                }
                            }
                        }
                    }
                }

                if (showAddPatientDialog) {
                    AlertDialog(
                        onDismissRequest = { showAddPatientDialog = false },
                        title = { Text("Agregar Paciente", fontSize = 30.sp) },
                        text = {
                            Column {
                                TextField(
                                    value = nombre,
                                    onValueChange = { nombre = it },
                                    label = { Text("Nombre") },
                                    modifier = Modifier.fillMaxWidth(),
                                    textStyle = TextStyle(fontSize = 25.sp)
                                )
                            }
                        },
                        confirmButton = {
                            Button(onClick = {
                                viewModel.agregarPaciente(nombre)
                                showAddPatientDialog = false
                                nombre = "" // Resetea el campo de entrada después de agregar
                            }) {
                                Text("Confirmar")
                            }
                        },
                        dismissButton = {
                            Button(onClick = {
                                showAddPatientDialog = false
                                nombre = "" // Resetea el campo de entrada cuando se cierra el diálogo
                            }) {
                                Text("Cerrar")
                            }
                        }
                    )
                }
            }
        }
    )
}