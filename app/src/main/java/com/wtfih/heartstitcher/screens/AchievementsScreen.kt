package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.ButtonComponent
import com.wtfih.heartstitcher.components.DividerTextComponent
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.components.NormalTextComponent
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler

@Composable
fun AchievementsScreen() {
    val storage = Firebase.storage
    var storageRef = storage.reference
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.achievements))
            Spacer(modifier = Modifier.height(40.dp))
            NormalTextComponent(value = stringResource(id = R.string.achievements_into))
            Spacer(modifier = Modifier.weight(1f))
            ButtonComponent(value = stringResource(id = R.string.add), onButtonClicked = { HeartStitcherRouter.navigateTo(Screen.AddAchievementScreen)})
            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent()
            Spacer(modifier = Modifier.height(20.dp))
            ButtonComponent(value = stringResource(id = R.string.browse), onButtonClicked = { HeartStitcherRouter.navigateTo(Screen.BrowseAchievementScreen)})
            Spacer(modifier = Modifier.height(20.dp))
        }
        SystemBackButtonHandler {
            HeartStitcherRouter.navigateTo(Screen.HomeScreen)
        }
    }
}