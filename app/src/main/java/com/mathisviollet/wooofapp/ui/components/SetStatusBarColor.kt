package com.mathisviollet.wooofapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetStatusBarColor(topColor: Color, bottomColor: Color) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
//        systemUiController.isNavigationBarVisible = false
        systemUiController.setSystemBarsColor(topColor)
        systemUiController.setNavigationBarColor(bottomColor)
    }
}