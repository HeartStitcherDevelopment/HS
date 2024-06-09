package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.ButtonComponent
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.components.SettingsIconComponent
import com.wtfih.heartstitcher.components.ThemeIcon
import com.wtfih.heartstitcher.data.ThemeData
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler
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
import com.wtfih.heartstitcher.ui.theme.Solitude1
import com.wtfih.heartstitcher.ui.theme.Solitude2
import com.wtfih.heartstitcher.ui.theme.SolitudeTheme
import com.wtfih.heartstitcher.ui.theme.Space1
import com.wtfih.heartstitcher.ui.theme.Space2
import com.wtfih.heartstitcher.ui.theme.SpaceTheme


@Composable
fun SettingsScreen(themeData: ThemeData = viewModel()) {
    val db = Firebase.firestore
    val id = Firebase.auth.currentUser!!.uid
    val color by remember {  derivedStateOf { themeData.color} }
    val color1 by remember {  derivedStateOf { themeData.color1} }
    val color2 by remember {  derivedStateOf { themeData.color2} }
    val font by remember { derivedStateOf { themeData.font} }
    val music = remember {  derivedStateOf{ themeData.music.value } }
    val sounds = remember {  derivedStateOf{ themeData.sounds.value } }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.settings), c = color.value)

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Themes",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 25.sp,
                    color = color.value,
                    fontFamily = font.value.toFontFamily()
                )
                Spacer(modifier = Modifier.width(5.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    color = color.value,
                    thickness = 3.dp
                )
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item{ ThemeIcon(
                    value = stringResource(id = R.string.rain),
                    onIconClicked = { themeData.setTheme(0);
                        db.collection("users").document(id).update("theme", 0)
                                    themeData.setColor(RainTheme)
                            themeData.setColor1(Rain1)
                            themeData.setColor2(Rain2)
                            themeData.setFont(Font(R.font.rain_font))},
                    painterResource = R.drawable.rainy_background,
                    textColor = RainTheme,
                    fontResource = Font(R.font.rain_font))
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.future),
                    onIconClicked = { themeData.setTheme(2);
                        db.collection("users").document(id).update("theme", 2);
                        themeData.setColor(FuturisticTheme)
                        themeData.setColor1(Futuristic1)
                        themeData.setColor2(Futuristic2)
                        themeData.setFont(Font(R.font.futuristic_font))},
                    painterResource = R.drawable.futuristic_background,
                    textColor = FuturisticTheme,
                    fontResource = Font(R.font.futuristic_font))
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.chill),
                    onIconClicked = { themeData.setTheme(3);
                        db.collection("users").document(id).update("theme", 3);
                        themeData.setColor(ChillTheme)
                        themeData.setColor1(Chill1)
                        themeData.setColor2(Chill2)
                        themeData.setFont(Font(R.font.chill_font))},
                    painterResource = R.drawable.chill_background,
                    textColor = ChillTheme,
                    fontResource = Font(R.font.chill_font))
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.japan),
                    onIconClicked = { themeData.setTheme(1);
                        db.collection("users").document(id).update("theme", 1);
                        themeData.setColor(JapanTheme)
                        themeData.setColor1(Japan1)
                        themeData.setColor2(Japan2)
                        themeData.setFont(Font(R.font.japan_font))},
                    painterResource = R.drawable.japanese_background,
                    textColor = JapanTheme,
                    fontResource = Font(R.font.japan_font)
                )
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.anime),
                    onIconClicked = { themeData.setTheme(4);
                        db.collection("users").document(id).update("theme", 4);
                        themeData.setColor(AnimeTheme)
                        themeData.setColor1(Anime1)
                        themeData.setColor2(Anime2)
                        themeData.setFont(Font(R.font.anime_font))},
                    painterResource = R.drawable.anime_girl_background,
                    textColor = AnimeTheme,
                    fontResource = Font(R.font.anime_font))
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.retro),
                    onIconClicked = { themeData.setTheme(5);
                        db.collection("users").document(id).update("theme", 5);
                        themeData.setColor(RetroTheme)
                        themeData.setColor1(Retro1)
                        themeData.setColor2(Retro2)
                        themeData.setFont(Font(R.font.retro_font))},
                    painterResource = R.drawable.retro_background,
                    textColor = RetroTheme,
                    fontResource = Font(R.font.retro_font))
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.solitude),
                    onIconClicked = { themeData.setTheme(6);
                        db.collection("users").document(id).update("theme", 6);
                        themeData.setColor(SolitudeTheme)
                        themeData.setColor1(Solitude1)
                        themeData.setColor2(Solitude2)
                        themeData.setFont(Font(R.font.solitude_font))},
                    painterResource = R.drawable.solitude_background,
                    textColor = SolitudeTheme,
                    fontResource = Font(R.font.solitude_font))
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.space),
                    onIconClicked = { themeData.setTheme(7);
                        db.collection("users").document(id).update("theme", 7);
                        themeData.setColor(SpaceTheme)
                        themeData.setColor1(Space1)
                        themeData.setColor2(Space2)
                        themeData.setFont(Font(R.font.space_font))},
                    painterResource = R.drawable.space_background,
                    textColor = SpaceTheme,
                    fontResource = Font(R.font.space_font))
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.love),
                    onIconClicked = { themeData.setTheme(8);
                        db.collection("users").document(id).update("theme", 8);
                        themeData.setColor(LoveTheme)
                        themeData.setColor1(Love1)
                        themeData.setColor2(Love2)
                        themeData.setFont(Font(R.font.love_font))},
                    painterResource = R.drawable.love_background,
                    textColor = LoveTheme,
                    fontResource = Font(R.font.love_font))
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.forest),
                    onIconClicked = { themeData.setTheme(9);
                        db.collection("users").document(id).update("theme", 9);
                        themeData.setColor(ForestTheme)
                        themeData.setColor1(Forest1)
                        themeData.setColor2(Forest2)
                        themeData.setFont(Font(R.font.forest_font))},
                    painterResource = R.drawable.forest_background,
                    textColor = ForestTheme,
                    fontResource = Font(R.font.forest_font))
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.psycho),
                    onIconClicked = { themeData.setTheme(10);
                        db.collection("users").document(id).update("theme", 10);
                        themeData.setColor(PsychoTheme)
                        themeData.setColor1(Psycho1)
                        themeData.setColor2(Psycho2)
                        themeData.setFont(Font(R.font.psycho_font))},
                    painterResource = R.drawable.psycho_background,
                    textColor = PsychoTheme,
                    fontResource = Font(R.font.psycho_font))
                }
                /*item{ ThemeIcon(
                    value = stringResource(id = R.string.roblox),
                    onIconClicked = { themeData.setTheme(11);
                        db.collection("users").document(id).update("theme", 11);
                        themeData.setColor(RobloxTheme)
                        themeData.setColor1(Roblox1)
                        themeData.setColor2(Roblox2)},
                    painterResource = R.drawable.roblox_background,
                    textColor = RobloxTheme
                )
                    Spacer(modifier = Modifier.width(5.dp))}*/
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Audio",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 25.sp,
                    color = color.value,
                    fontFamily = font.value.toFontFamily()
                )

                Spacer(modifier = Modifier.width(5.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    color = color.value,
                    thickness = 3.dp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                SettingsIconComponent(
                    value = stringResource(id = R.string.music),
                    onIconClicked = { themeData.setMusic()
                        db.collection("users").document(id).update("music", themeData.music.value) },
                    isEnabled = true,
                    painterResource(id = R.drawable.music_on),
                    alternatePainterResource = painterResource(id = R.drawable.music_off),
                    useAlternate = music.value,
                    color = color.value,
                    color1 = color1.value,
                    color2 = color2.value
                )
                Spacer(modifier = Modifier.width(50.dp))
                SettingsIconComponent(
                    value = stringResource(id = R.string.sounds),
                    onIconClicked = { themeData.setSounds()
                        db.collection("users").document(id).update("sounds", themeData.sounds.value) },
                    isEnabled = true,
                    painterResource(id = R.drawable.sounds_on),
                    alternatePainterResource = painterResource(id = R.drawable.sounds_off),
                    useAlternate = sounds.value,
                    color = color.value,
                    color1 = color1.value,
                    color2 = color2.value
                )
            }
            Spacer(modifier = Modifier.height(30.dp))

            ButtonComponent(value = "testing", onButtonClicked = { HeartStitcherRouter.navigateTo(Screen.WakeUpScreen)})
            ButtonComponent(value = "testing", onButtonClicked = { HeartStitcherRouter.navigateTo(Screen.NotificationScreen)})
        }
        SystemBackButtonHandler {
            HeartStitcherRouter.navigateTo(Screen.HomeScreen)
        }
    }
}