package com.example.calcular9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.calcular9.navigate.NavGraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.calcular9.navigation.NavManager


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = Color.White) {
                    val navController = rememberNavController()
                    NavGraph(navController)
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavManager(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}