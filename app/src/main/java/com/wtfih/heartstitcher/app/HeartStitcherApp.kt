package com.wtfih.heartstitcher.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.screens.AchievementsScreen
import com.wtfih.heartstitcher.screens.HomeScreen
import com.wtfih.heartstitcher.screens.LoadingScreen
import com.wtfih.heartstitcher.screens.LoginScreen
import com.wtfih.heartstitcher.screens.NotepadScreen
import com.wtfih.heartstitcher.screens.NutritionScreen
import com.wtfih.heartstitcher.screens.PanicScreen
import com.wtfih.heartstitcher.screens.SettingsScreen
import com.wtfih.heartstitcher.screens.SignUpScreen
import com.wtfih.heartstitcher.screens.SleepScreen
import com.wtfih.heartstitcher.screens.TaskScreen
import com.wtfih.heartstitcher.screens.TermsAndConditionsScreen
import com.wtfih.heartstitcher.screens.WheelScreen


@Composable
fun HeartSitcherApp(){
    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.White
    ){
        Crossfade(targetState = HeartStitcherRouter.currentScreen){ currentState->
            when(currentState.value){
                is Screen.SignUpScreen ->{
                    SignUpScreen()
                }
                is Screen.TermsAndConditionsScreen ->{
                    TermsAndConditionsScreen()
                }
                is Screen.LoginScreen ->{
                    LoginScreen()
                }
                is Screen.HomeScreen ->{
                    HomeScreen()
                }
                is Screen.PanicScreen ->{
                    PanicScreen()
                }
                is Screen.WheelScreen ->{
                    WheelScreen()
                }
                is Screen.AchievementsScreen ->{
                    AchievementsScreen()
                }
                is Screen.NotepadScreen ->{
                    NotepadScreen()
                }
                is Screen.NutritionScreen ->{
                    NutritionScreen()
                }
                is Screen.SettingsScreen ->{
                    SettingsScreen()
                }
                is Screen.SleepScreen ->{
                    SleepScreen()
                }
                is Screen.TaskScreen ->{
                    TaskScreen()
                }
                is Screen.LoadingScreen ->{
                    LoadingScreen(loadingTimeMillis = 3000, onLoadingComplete =  {HeartStitcherRouter.navigateTo(Screen.TaskScreen)}) // Default loading time in milliseconds
                     // Callback function to execute after loading)
                }
            }
        }
    }
}


