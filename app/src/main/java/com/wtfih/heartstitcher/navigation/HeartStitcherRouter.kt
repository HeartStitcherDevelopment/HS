package com.wtfih.heartstitcher.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {

    object SignUpScreen : Screen()
    object TermsAndConditionsScreen : Screen()
    object LoginScreen : Screen()
    object HomeScreen : Screen()
    object PanicScreen : Screen()
    object WheelScreen : Screen()
    object AchievementsScreen : Screen()
    object CheerUpScreen : Screen()
    object NotepadScreen : Screen()
    object NutritionScreen : Screen()
    object SettingsScreen : Screen()
    object SleepScreen :  Screen()
}

object HeartStitcherRouter {

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigateTo(destination : Screen){
        currentScreen.value = destination

    }
}