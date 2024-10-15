package com.example.calcular9.onboardingviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.calcular9.R
import com.example.calcular9.data.PageData
import com.example.calcular9.dataStore.StoreBoarding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainOnBoarding(modifier: Modifier, navController: NavController, store: StoreBoarding) {
    val items = ArrayList<PageData>()

    items.add(
        PageData(
            R.raw.list,
            "Lista de Pacientes",
            "Crea un registro de todos tus pacientes y sus datos más importantes, para mantener un orden y seguimiento de cada uno."
        )
    )
    items.add(
        PageData(
            R.raw.fast,
            "Fácil de Usar",
            "Un registro intuitivo y rápido."
        )
    )
    items.add(
        PageData(
            R.raw.calculadora,
            "Calculadora de IMC",
            "Cuentas con una calculadora de índice de masa corporal para registrar rápidamente los datos de tus pacientes."
        )
    )

    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0
    )

    OnBoardingPager(
        item = items,
        pagerState = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White),
        navController,
        store
    )
}