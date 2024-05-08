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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.components.IconComponent
import com.wtfih.heartstitcher.components.ThemeIcon
import com.wtfih.heartstitcher.data.ThemeData
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler
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


@Composable
fun SettingsScreen(themeData: ThemeData = viewModel()) {
    val db = Firebase.firestore
    val id = Firebase.auth.currentUser!!.uid
    val color by remember {  derivedStateOf { themeData.color} }
    val color1 by remember {  derivedStateOf { themeData.color1} }
    val color2 by remember {  derivedStateOf { themeData.color2} }
    Box(
        modifier = Modifier
            .fillMaxSize()
            //.background(brush = Brush.horizontalGradient(listOf(Purple, Blue)))
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
                    color = color.value)

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
                                    themeData.setColor(DefaultTheme)
                            themeData.setColor1(Default1)
                            themeData.setColor2(Default2)},
                    painterResource = R.drawable.rainy_background,
                    textColor = DefaultTheme)
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.japan),
                    onIconClicked = { themeData.setTheme(1);
                        db.collection("users").document(id).update("theme", 1);
                        themeData.setColor(JapanTheme)
                        themeData.setColor1(Japan1)
                        themeData.setColor2(Japan2)},
                    painterResource = R.drawable.japanese_background,
                    textColor = JapanTheme)
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.future),
                    onIconClicked = { themeData.setTheme(2);
                        db.collection("users").document(id).update("theme", 2);
                        themeData.setColor(FuturisticTheme)
                        themeData.setColor1(Futuristic1)
                        themeData.setColor2(Futuristic2)},
                    painterResource = R.drawable.futuristic_background,
                    textColor = FuturisticTheme)
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.chill),
                    onIconClicked = { themeData.setTheme(3);
                        db.collection("users").document(id).update("theme", 3);
                        themeData.setColor(ChillTheme)
                        themeData.setColor1(Chill1)
                        themeData.setColor2(Chill2)},
                    painterResource = R.drawable.chill_background,
                    textColor = ChillTheme)
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.anime),
                    onIconClicked = { themeData.setTheme(4);
                        db.collection("users").document(id).update("theme", 4);
                        themeData.setColor(AnimeTheme)
                        themeData.setColor1(Anime1)
                        themeData.setColor2(Anime2) },
                    painterResource = R.drawable.anime_girl_background,
                    textColor = AnimeTheme)
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.retro),
                    onIconClicked = { themeData.setTheme(5);
                        db.collection("users").document(id).update("theme", 5);
                        themeData.setColor(RetroTheme)
                        themeData.setColor1(Retro1)
                        themeData.setColor2(Retro2)},
                    painterResource = R.drawable.retro_background,
                    textColor = RetroTheme)
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.roblox),
                    onIconClicked = { themeData.setTheme(6);
                        db.collection("users").document(id).update("theme", 6);
                        themeData.setColor(RobloxTheme)
                        themeData.setColor1(Roblox1)
                        themeData.setColor2(Roblox2)},
                    painterResource = R.drawable.roblox_background,
                    textColor = RobloxTheme)
                    Spacer(modifier = Modifier.width(5.dp))}
                item{ ThemeIcon(
                    value = stringResource(id = R.string.baka),
                    onIconClicked = { themeData.setTheme(7);
                        db.collection("users").document(id).update("theme", 7);
                        themeData.setColor(BakaTheme)
                        themeData.setColor1(Baka1)
                        themeData.setColor2(Baka2)},
                    painterResource = R.drawable.baka_background,
                    textColor = BakaTheme)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Audio",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 25.sp,
                    color = color.value)

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
                IconComponent(
                    value = stringResource(id = R.string.music),
                    onIconClicked = {
                         },
                    isEnabled = true,
                    painterResource(id = R.drawable.music_on),
                    color = color.value,
                    color1 = color1.value,
                    color2 = color2.value
                )
                Spacer(modifier = Modifier.width(50.dp))
                IconComponent(
                    value = stringResource(id = R.string.sounds),
                    onIconClicked = {
                         },
                    isEnabled = true,
                    painterResource(id = R.drawable.sounds_on),
                    color = color.value,
                    color1 = color1.value,
                    color2 = color2.value
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Contacts",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 25.sp,
                    color = color.value)

                Spacer(modifier = Modifier.width(5.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    color = color.value,
                    thickness = 3.dp
                )
            }
        }
        SystemBackButtonHandler {
            HeartStitcherRouter.navigateTo(Screen.HomeScreen)
        }
    }
}