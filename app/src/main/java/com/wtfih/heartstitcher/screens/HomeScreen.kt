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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.CheerUpButtonComponent
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.components.IconComponent
import com.wtfih.heartstitcher.data.SignUpViewModel
import com.wtfih.heartstitcher.data.ThemeData
import com.wtfih.heartstitcher.data.UserDataViewModel
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen

@Composable
fun HomeScreen(signUpViewModel: SignUpViewModel = SignUpViewModel(),themeData: ThemeData = viewModel(), dataViewModel: UserDataViewModel = viewModel()) {
    themeData.LogInFLag()
    val color by remember {  derivedStateOf { themeData.color.value } }
    val color1 by remember {  derivedStateOf { themeData.color1.value } }
    val color2 by remember {  derivedStateOf { themeData.color2.value } }
    val font by remember { derivedStateOf { themeData.font.value }}
    val name =  dataViewModel.state.value["first"].toString()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {item {
            Spacer(modifier = Modifier.height(20.dp))
        }

            item {
                HeadingTextComponent(value = "Welcome back,\n $name!", c = color, fontResource = font)
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            /*item {
                PanicButtonComponent(
                    onButtonClicked = {
                        HeartStitcherRouter.navigateTo(Screen.PanicScreen)
                    },
                    isEnabled = true
                )
            }*/

            item {
                Spacer(modifier = Modifier.height(30.dp))
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column {
                        IconComponent(
                            value = stringResource(id = R.string.notepad),
                            onIconClicked = { HeartStitcherRouter.navigateTo(Screen.NotepadScreen) },
                            isEnabled = true,
                            painterResource(id = R.drawable.notepad),
                            color = color, color1 = color1, color2 = color2,
                            fontResource = font
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        IconComponent(
                            value = stringResource(id = R.string.nutrition),
                            onIconClicked = {
                                HeartStitcherRouter.navigateTo(Screen.NutritionScreen) },
                            isEnabled = true,
                            painterResource(id = R.drawable.nutrition),
                            color = color, color1 = color1, color2 = color2,
                            fontResource = font
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        CheerUpButtonComponent(
                            value = stringResource(id = R.string.cheer),
                            isEnabled = true,
                            painterResource = painterResource(id = R.drawable.cheer),
                            color = color, color1 = color1, color2 = color2,
                            fontResource = font
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        IconComponent(
                            value = stringResource(id = R.string.settings),
                            onIconClicked = {
                                HeartStitcherRouter.navigateTo(Screen.SettingsScreen) },
                            isEnabled = true,
                            painterResource(id = R.drawable.settings),
                            color = color, color1 = color1, color2 = color2,
                            fontResource = font
                        )
                    }

                    Spacer(modifier = Modifier.width(30.dp))

                    Column {
                        IconComponent(
                            value = stringResource(id = R.string.task),
                            onIconClicked = { HeartStitcherRouter.navigateTo(Screen.TaskScreen) },
                            isEnabled = true,
                            painterResource(id = R.drawable.wheel),
                            color = color, color1 = color1, color2 = color2,
                            fontResource = font
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        IconComponent(
                            value = stringResource(id = R.string.sleep),
                            onIconClicked = {
                                HeartStitcherRouter.navigateTo(Screen.SleepScreen) },
                            isEnabled = true,
                            painterResource(id = R.drawable.sleep),
                            color = color, color1 = color1, color2 = color2,
                            fontResource = font
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        IconComponent(
                            value = stringResource(id = R.string.achievements),
                            onIconClicked = {
                                HeartStitcherRouter.navigateTo(Screen.AchievementsScreen) },
                            isEnabled = true,
                            painterResource(id = R.drawable.achievements),
                            color = color, color1 = color1, color2 = color2,
                            fontResource = font
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        IconComponent(
                            value = stringResource(id = R.string.logout),
                            onIconClicked = {themeData.logOutFlag()
                                signUpViewModel.logout() },
                            isEnabled = true,
                            painterResource(id = R.drawable.logout),
                            color = color, color1 = color1, color2 = color2,
                            fontResource = font
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun ScreenPreview(){
    HomeScreen()
}