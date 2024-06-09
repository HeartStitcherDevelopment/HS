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
    object NotepadScreen : Screen()
    object NutritionScreen : Screen()
    object SettingsScreen : Screen()
    object SleepScreen :  Screen()
    object TaskScreen : Screen()
    object LoadingScreen : Screen()
    object LoadingScreen2 : Screen()
    object AddAchievementScreen : Screen()
    object BrowseAchievementScreen : Screen()
    object PlaceHolderScreen : Screen()
    object DreamsScreen : Screen()
    object WakeUpScreen : Screen()
    object NotificationScreen : Screen()
    object BrowseNotesScreen : Screen()
    object BrowseDreamsScreen : Screen()
}

object HeartStitcherRouter {

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.PlaceHolderScreen)

    fun navigateTo(destination : Screen){
        currentScreen.value = destination

    }
}