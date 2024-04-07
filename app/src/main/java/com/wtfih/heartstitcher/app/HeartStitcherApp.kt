package com.wtfih.heartstitcher.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.screens.HomeScreen
import com.wtfih.heartstitcher.screens.LoginScreen
import com.wtfih.heartstitcher.screens.SignUpScreen
import com.wtfih.heartstitcher.screens.TermsAndConditionsScreen


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
            }
        }
    }
}

