package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.components.IconComponent
import com.wtfih.heartstitcher.components.PanicButtonComponent
import com.wtfih.heartstitcher.data.SignUpViewModel
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.ui.theme.Blue
import com.wtfih.heartstitcher.ui.theme.Purple

@Composable
fun HomeScreen(signUpViewModel: SignUpViewModel = SignUpViewModel()) {
    val Current_User = Firebase.auth.currentUser!!.uid
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(listOf(Purple, Blue))
            )
            .padding(28.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            item {
                HeadingTextComponent(value = stringResource(id = R.string.home))
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
            }

            item {
                PanicButtonComponent(
                    onButtonClicked = {
                        HeartStitcherRouter.navigateTo(Screen.PanicScreen)
                    },
                    isEnabled = true
                )
            }

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
                            painterResource(id = R.drawable.notepad)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        IconComponent(
                            value = stringResource(id = R.string.nutrition),
                            onIconClicked = { HeartStitcherRouter.navigateTo(Screen.NutritionScreen) },
                            isEnabled = true,
                            painterResource(id = R.drawable.nutrition)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        IconComponent(
                            value = stringResource(id = R.string.cheer),
                            onIconClicked = { HeartStitcherRouter.navigateTo(Screen.CheerUpScreen) },
                            isEnabled = true,
                            painterResource(id = R.drawable.cheer)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        IconComponent(
                            value = stringResource(id = R.string.settings),
                            onIconClicked = { HeartStitcherRouter.navigateTo(Screen.SettingsScreen) },
                            isEnabled = true,
                            painterResource(id = R.drawable.settings)
                        )
                    }

                    Spacer(modifier = Modifier.width(30.dp))

                    Column {
                        IconComponent(
                            value = stringResource(id = R.string.wheel),
                            onIconClicked = { HeartStitcherRouter.navigateTo(Screen.WheelScreen) },
                            isEnabled = true,
                            painterResource(id = R.drawable.wheel)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        IconComponent(
                            value = stringResource(id = R.string.sleep),
                            onIconClicked = { HeartStitcherRouter.navigateTo(Screen.SleepScreen) },
                            isEnabled = true,
                            painterResource(id = R.drawable.sleep)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        IconComponent(
                            value = stringResource(id = R.string.achievements),
                            onIconClicked = { HeartStitcherRouter.navigateTo(Screen.AchievementsScreen) },
                            isEnabled = true,
                            painterResource(id = R.drawable.achievements)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        IconComponent(
                            value = stringResource(id = R.string.logout),
                            onIconClicked = { signUpViewModel.logout() },
                            isEnabled = true,
                            painterResource(id = R.drawable.logout)
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