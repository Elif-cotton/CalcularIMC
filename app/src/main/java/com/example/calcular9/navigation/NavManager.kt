package com.example.calcular9.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calcular9.dataStore.StoreBoarding
import com.example.calcular9.onboardingviews.MainOnBoarding
import com.example.calcular9.view.PatientListView
import com.example.calcular9.view.SplashScreen

@Composable
fun NavManager(modifier: Modifier) {
    val context = LocalContext.current
    val dataStore = StoreBoarding(context)
    val store = dataStore.getBoarding.collectAsState(initial = false)

    val navController = rememberNavController()
    NavHost(navController, startDestination = if (store.value) "Home" else "Splash") {
        composable("OnBoarding") {
            MainOnBoarding(modifier = Modifier, navController, dataStore)
        }
        composable("Home") {
            PatientListView(navController)
        }
        composable("Splash") {
            SplashScreen(navController, store.value)
        }
    }
}