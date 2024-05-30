package com.wtfih.heartstitcher.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.MusicPlayer
import com.wtfih.heartstitcher.data.Globals.ButtonColor1
import com.wtfih.heartstitcher.data.Globals.ButtonColor2
import com.wtfih.heartstitcher.data.Globals.ColorTheme
import com.wtfih.heartstitcher.data.Globals.Font
import com.wtfih.heartstitcher.data.ThemeData
import com.wtfih.heartstitcher.data.UserDataViewModel
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.screens.AchievementsScreen
import com.wtfih.heartstitcher.screens.AddAchievementScreen
import com.wtfih.heartstitcher.screens.BrowseAchievementScreen
import com.wtfih.heartstitcher.screens.DreamsScreen
import com.wtfih.heartstitcher.screens.HomeScreen
import com.wtfih.heartstitcher.screens.LoadingScreen
import com.wtfih.heartstitcher.screens.LoginScreen
import com.wtfih.heartstitcher.screens.NotepadScreen
import com.wtfih.heartstitcher.screens.NutritionScreen
import com.wtfih.heartstitcher.screens.PanicScreen
import com.wtfih.heartstitcher.screens.PlaceHolderScreen
import com.wtfih.heartstitcher.screens.SettingsScreen
import com.wtfih.heartstitcher.screens.SignUpScreen
import com.wtfih.heartstitcher.screens.SleepScreen
import com.wtfih.heartstitcher.screens.TaskScreen
import com.wtfih.heartstitcher.screens.TermsAndConditionsScreen
import com.wtfih.heartstitcher.screens.WheelScreen
import com.wtfih.heartstitcher.ui.theme.Anime1
import com.wtfih.heartstitcher.ui.theme.Anime2
import com.wtfih.heartstitcher.ui.theme.AnimeTheme
import com.wtfih.heartstitcher.ui.theme.Chill1
import com.wtfih.heartstitcher.ui.theme.Chill2
import com.wtfih.heartstitcher.ui.theme.ChillTheme
import com.wtfih.heartstitcher.ui.theme.Forest1
import com.wtfih.heartstitcher.ui.theme.Forest2
import com.wtfih.heartstitcher.ui.theme.ForestTheme
import com.wtfih.heartstitcher.ui.theme.Futuristic1
import com.wtfih.heartstitcher.ui.theme.Futuristic2
import com.wtfih.heartstitcher.ui.theme.FuturisticTheme
import com.wtfih.heartstitcher.ui.theme.Japan1
import com.wtfih.heartstitcher.ui.theme.Japan2
import com.wtfih.heartstitcher.ui.theme.JapanTheme
import com.wtfih.heartstitcher.ui.theme.Love1
import com.wtfih.heartstitcher.ui.theme.Love2
import com.wtfih.heartstitcher.ui.theme.LoveTheme
import com.wtfih.heartstitcher.ui.theme.Psycho1
import com.wtfih.heartstitcher.ui.theme.Psycho2
import com.wtfih.heartstitcher.ui.theme.PsychoTheme
import com.wtfih.heartstitcher.ui.theme.Rain1
import com.wtfih.heartstitcher.ui.theme.Rain2
import com.wtfih.heartstitcher.ui.theme.RainTheme
import com.wtfih.heartstitcher.ui.theme.Retro1
import com.wtfih.heartstitcher.ui.theme.Retro2
import com.wtfih.heartstitcher.ui.theme.RetroTheme
import com.wtfih.heartstitcher.ui.theme.Roblox1
import com.wtfih.heartstitcher.ui.theme.Roblox2
import com.wtfih.heartstitcher.ui.theme.RobloxTheme
import com.wtfih.heartstitcher.ui.theme.Solitude1
import com.wtfih.heartstitcher.ui.theme.Solitude2
import com.wtfih.heartstitcher.ui.theme.SolitudeTheme
import com.wtfih.heartstitcher.ui.theme.Space1
import com.wtfih.heartstitcher.ui.theme.Space2
import com.wtfih.heartstitcher.ui.theme.SpaceTheme

@Composable
fun HeartSitcherApp(themeData: ThemeData = viewModel(), dataViewModel: UserDataViewModel = viewModel()){
    val login by remember { derivedStateOf { themeData.login} }
    Surface(modifier = Modifier.fillMaxSize())
    {
        if(login.value) {
            dataViewModel.refresh()
            var flag = remember { mutableStateOf(true) }
            val current = remember { dataViewModel.state.value["theme"] as? Long ?: 0 }.toInt()
            val c_music = remember  { dataViewModel.state.value["music"] as? Boolean ?: true}
            val c_sounds = remember  { dataViewModel.state.value["sounds"] as? Boolean ?: true}
            if(flag.value){
                flag.value = false
                if(themeData.music.value != c_music) themeData.setMusic()
                themeData.setTheme(current)
                when (current){
                    0 -> {themeData.setColor(RainTheme)
                        themeData.setColor1(Rain1)
                        themeData.setColor2(Rain2)
                        themeData.setFont(Font(R.font.rain_font))}
                    1 -> {themeData.setColor(JapanTheme)
                        themeData.setColor1(Japan1)
                        themeData.setColor2(Japan2)
                        themeData.setFont(Font(R.font.japan_font))}
                    2 -> {themeData.setColor(FuturisticTheme)
                        themeData.setColor1(Futuristic1)
                        themeData.setColor2(Futuristic2)
                        themeData.setFont(Font(R.font.futuristic_font))}
                    3 -> {themeData.setColor(ChillTheme)
                        themeData.setColor1(Chill1)
                        themeData.setColor2(Chill2)
                        themeData.setFont(Font(R.font.chill_font))}
                    4 -> {themeData.setColor(AnimeTheme)
                        themeData.setColor1(Anime1)
                        themeData.setColor2(Anime2)
                        themeData.setFont(Font(R.font.anime_font))}
                    5 -> {themeData.setColor(RetroTheme)
                        themeData.setColor1(Retro1)
                        themeData.setColor2(Retro2)
                        themeData.setFont(Font(R.font.retro_font))}
                    6 -> {themeData.setColor(SolitudeTheme)
                        themeData.setColor1(Solitude1)
                        themeData.setColor2(Solitude2)
                        themeData.setFont(Font(R.font.solitude_font))}
                    7 -> {themeData.setColor(SpaceTheme)
                        themeData.setColor1(Space1)
                        themeData.setColor2(Space2)
                        themeData.setFont(Font(R.font.space_font))}
                    8 -> {themeData.setColor(LoveTheme)
                        themeData.setColor1(Love1)
                        themeData.setColor2(Love2)
                        themeData.setFont(Font(R.font.love_font))}
                    9 -> {themeData.setColor(ForestTheme)
                        themeData.setColor1(Forest1)
                        themeData.setColor2(Forest2)
                        themeData.setFont(Font(R.font.forest_font))}
                    10 -> {themeData.setColor(PsychoTheme)
                        themeData.setColor1(Psycho1)
                        themeData.setColor2(Psycho2)
                        themeData.setFont(Font(R.font.psycho_font))}
                    11 -> {themeData.setColor(RobloxTheme)
                        themeData.setColor1(Roblox1)
                        themeData.setColor2(Roblox2)}
                    else -> {themeData.setColor(RainTheme)
                        themeData.setColor1(Rain1)
                        themeData.setColor2(Rain2)
                        themeData.setFont(Font(R.font.rain_font))}
                }
            }
                val th = remember { derivedStateOf { themeData.theme.value } }
                val music = remember {  derivedStateOf{ themeData.music.value } }
                val sounds = remember { derivedStateOf { themeData.sounds.value }}
                when (th.value) {
                    0 -> {
                        ColorTheme = RainTheme
                        ButtonColor1 = Rain1
                        ButtonColor2 = Rain2
                        Font = Font(R.font.rain_font)
                        MusicPlayer(loop = true, audioResourceId = R.raw.lights, isPlaying = music.value)
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
                        Font = Font(R.font.japan_font)
                        MusicPlayer(loop = true, audioResourceId = R.raw.hanamura, isPlaying = music.value)
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
                        Font = Font(R.font.futuristic_font)
                        MusicPlayer(loop = true, audioResourceId = R.raw.make_this_right, isPlaying = music.value)
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
                        Font = Font(R.font.chill_font)
                        MusicPlayer(loop = true, audioResourceId = R.raw.slow_boat, isPlaying = music.value)
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
                        Font = Font(R.font.anime_font)
                        MusicPlayer(loop = true, audioResourceId = R.raw.mikichan, isPlaying = music.value)
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
                        Font = Font(R.font.retro_font)
                        MusicPlayer(loop = true, audioResourceId = R.raw.howling_grotto, isPlaying = music.value)
                        Image(
                            painter = painterResource(id = R.drawable.retro_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    6 -> {
                        ColorTheme = SolitudeTheme
                        ButtonColor1 = Solitude1
                        ButtonColor2 = Solitude2
                        Font = Font(R.font.solitude_font)
                        MusicPlayer(loop = true, audioResourceId = R.raw.solo_made, isPlaying = music.value)
                        Image(
                            painter = painterResource(id = R.drawable.solitude_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    7 -> {
                        ColorTheme = SpaceTheme
                        ButtonColor1 = Space1
                        ButtonColor2 = Space2
                        Font = Font(R.font.space_font)
                        MusicPlayer(loop = true, audioResourceId = R.raw.purple_eyes, isPlaying = music.value)
                        Image(
                            painter = painterResource(id = R.drawable.space_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                        }
                    8 -> {
                        ColorTheme = LoveTheme
                        ButtonColor1 = Love1
                        ButtonColor2 = Love2
                        Font = Font(R.font.love_font)
                        MusicPlayer(loop = true, audioResourceId = R.raw.love, isPlaying = music.value)
                        Image(
                            painter = painterResource(id = R.drawable.love_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    9 -> {
                        ColorTheme = ForestTheme
                        ButtonColor1 = Forest1
                        ButtonColor2 = Forest2
                        Font = Font(R.font.forest_font)
                        MusicPlayer(loop = true, audioResourceId = R.raw.flower_maiden, isPlaying = music.value)
                        Image(
                            painter = painterResource(id = R.drawable.forest_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    10 -> {
                        ColorTheme = PsychoTheme
                        ButtonColor1 = Psycho1
                        ButtonColor2 = Psycho2
                        Font = Font(R.font.psycho_font)
                        MusicPlayer(loop = true, audioResourceId = R.raw.psycho_theme, isPlaying = music.value)
                        Image(
                            painter = painterResource(id = R.drawable.psycho_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    11 -> {
                        ColorTheme = RobloxTheme
                        ButtonColor1 = Roblox1
                        ButtonColor2 = Roblox2
                        MusicPlayer(loop = true, audioResourceId = R.raw.roblox, isPlaying = music.value)
                        Image(
                            painter = painterResource(id = R.drawable.roblox_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    else -> {
                        ColorTheme = RainTheme
                        ButtonColor1 = Rain1
                        ButtonColor2 = Rain2
                        Font = Font(R.font.rain_font)
                        MusicPlayer(loop = true, audioResourceId = R.raw.lights, isPlaying = music.value)
                        Image(
                            painter = painterResource(id = R.drawable.rainy_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
        }
        else {
            ColorTheme = RainTheme
            ButtonColor1 = Rain1
            ButtonColor2 = Rain2
            Font = Font(R.font.rain_font)
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
                is Screen.AddAchievementScreen ->{
                    AddAchievementScreen()
                }
                is Screen.BrowseAchievementScreen ->{
                    BrowseAchievementScreen()
                }
                is Screen.PlaceHolderScreen ->{
                    PlaceHolderScreen()
                }
                is Screen.DreamsScreen ->{
                    DreamsScreen()
                }
            }
        }
    }
}


