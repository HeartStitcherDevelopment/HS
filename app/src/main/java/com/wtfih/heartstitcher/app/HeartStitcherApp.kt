package com.wtfih.heartstitcher.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.MusicPlayer
import com.wtfih.heartstitcher.data.Globals.ButtonColor1
import com.wtfih.heartstitcher.data.Globals.ButtonColor2
import com.wtfih.heartstitcher.data.Globals.ColorTheme
import com.wtfih.heartstitcher.data.Globals.flag
import com.wtfih.heartstitcher.data.ThemeData
import com.wtfih.heartstitcher.data.UserDataViewModel
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
import com.wtfih.heartstitcher.ui.theme.Anime1
import com.wtfih.heartstitcher.ui.theme.Anime2
import com.wtfih.heartstitcher.ui.theme.AnimeTheme
import com.wtfih.heartstitcher.ui.theme.Baka1
import com.wtfih.heartstitcher.ui.theme.Baka2
import com.wtfih.heartstitcher.ui.theme.BakaTheme
import com.wtfih.heartstitcher.ui.theme.Chill1
import com.wtfih.heartstitcher.ui.theme.Chill2
import com.wtfih.heartstitcher.ui.theme.ChillTheme
import com.wtfih.heartstitcher.ui.theme.Default1
import com.wtfih.heartstitcher.ui.theme.Default2
import com.wtfih.heartstitcher.ui.theme.DefaultTheme
import com.wtfih.heartstitcher.ui.theme.Futuristic1
import com.wtfih.heartstitcher.ui.theme.Futuristic2
import com.wtfih.heartstitcher.ui.theme.FuturisticTheme
import com.wtfih.heartstitcher.ui.theme.Japan1
import com.wtfih.heartstitcher.ui.theme.Japan2
import com.wtfih.heartstitcher.ui.theme.JapanTheme
import com.wtfih.heartstitcher.ui.theme.Retro1
import com.wtfih.heartstitcher.ui.theme.Retro2
import com.wtfih.heartstitcher.ui.theme.RetroTheme
import com.wtfih.heartstitcher.ui.theme.Roblox1
import com.wtfih.heartstitcher.ui.theme.Roblox2
import com.wtfih.heartstitcher.ui.theme.RobloxTheme

//MusicPlayer(loop = true, audioResourceId = R.raw.lights)

@Composable
fun HeartSitcherApp(themeData: ThemeData = viewModel(), dataViewModel: UserDataViewModel = viewModel()){
    val login by remember { derivedStateOf { themeData.login} }
    Surface(modifier = Modifier.fillMaxSize())
    {
        if(login.value) {
            val current = dataViewModel.state.value["theme"] as? Int ?: 0
            if(flag){
                flag = false
                themeData.setTheme(current)
                when (current){
                    0 -> {themeData.setColor1(Default1)
                        themeData.setColor2(Default2) }
                    1 -> {themeData.setColor1(Japan1)
                        themeData.setColor2(Japan2)}
                    2 -> {themeData.setColor1(Futuristic1)
                        themeData.setColor2(Futuristic2)}
                    3 -> {themeData.setColor1(Chill1)
                        themeData.setColor2(Chill2)}
                    4 -> {themeData.setColor1(Anime1)
                        themeData.setColor2(Anime2)}
                    5 -> {themeData.setColor1(Retro1)
                        themeData.setColor2(Retro2)}
                    6 -> {themeData.setColor1(Roblox1)
                        themeData.setColor2(Roblox2)}
                    7 -> {themeData.setColor1(Baka1)
                        themeData.setColor2(Baka2)}
                }
            }

                val th = remember { derivedStateOf { themeData.theme.value } }
                when (th.value) {

                    0 -> {
                        ColorTheme = DefaultTheme
                        ButtonColor1 = Default1
                        ButtonColor2 = Default2
                        MusicPlayer(loop = true, audioResourceId = R.raw.lights)
                        Image(
                            painter = painterResource(id = R.drawable.rainy_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    1 -> {
                        ColorTheme = JapanTheme
                        ButtonColor1 = Japan1
                        ButtonColor2 = Japan2
                        MusicPlayer(loop = true, audioResourceId = R.raw.hanamura)
                        Image(
                            painter = painterResource(id = R.drawable.japanese_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    2 -> {
                        ColorTheme = FuturisticTheme
                        ButtonColor1 = Futuristic1
                        ButtonColor2 = Futuristic2
                        MusicPlayer(loop = true, audioResourceId = R.raw.make_this_right)
                        Image(
                            painter = painterResource(id = R.drawable.futuristic_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    3 -> {
                        ColorTheme = ChillTheme
                        ButtonColor1 = Chill1
                        ButtonColor2 = Chill2
                        MusicPlayer(loop = true, audioResourceId = R.raw.slow_boat)
                        Image(
                            painter = painterResource(id = R.drawable.chill_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    4 -> {
                        ColorTheme = AnimeTheme
                        ButtonColor1 = Anime1
                        ButtonColor2 = Anime2
                        MusicPlayer(loop = true, audioResourceId = R.raw.mikichan)
                        Image(
                            painter = painterResource(id = R.drawable.anime_girl_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    5 -> {
                        ColorTheme = RetroTheme
                        ButtonColor1 = Retro1
                        ButtonColor2 = Retro2
                        MusicPlayer(loop = true, audioResourceId = R.raw.howling_grotto)
                        Image(
                            painter = painterResource(id = R.drawable.retro_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    6 -> {
                        ColorTheme = RobloxTheme
                        ButtonColor1 = Roblox1
                        ButtonColor2 = Roblox2
                        MusicPlayer(loop = true, audioResourceId = R.raw.roblox)
                        Image(
                            painter = painterResource(id = R.drawable.roblox_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    7 -> {
                        ColorTheme = BakaTheme
                        ButtonColor1 = Baka1
                        ButtonColor2 = Baka2
                        MusicPlayer(loop = true, audioResourceId = R.raw.mikichan)
                        Image(
                            painter = painterResource(id = R.drawable.baka_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
        }
        else {
            Image(
                painter = painterResource(id = R.drawable.purple_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        Crossfade(targetState = HeartStitcherRouter.currentScreen, label = ""){ currentState->
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
                    LoadingScreen(loadingTimeMillis = 1000, onLoadingComplete =  {HeartStitcherRouter.navigateTo(Screen.TaskScreen)}) // Default loading time in milliseconds
                }
                is Screen.LoadingScreen2 ->{
                    LoadingScreen(loadingTimeMillis = 1000, onLoadingComplete = {HeartStitcherRouter.navigateTo(Screen.WheelScreen)})
                }
            }
        }
    }
}


