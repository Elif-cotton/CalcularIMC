package com.example.calcular9.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calcular9.view.HomeView
import com.example.calcular9.view.PatientListView

sealed class Screen(val route: String) {
    object PatientList : Screen("patient_list")
    object Home : Screen("home")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.PatientList.route) {
        composable(route = Screen.PatientList.route) {
            PatientListView(navController)
        }
        composable(route = Screen.Home.route) {
            HomeView()
        }
    }
}