package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.AchievementsList
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.data.UserDataViewModel
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler

@Composable
fun BrowseAchievementScreen(dataViewModel: UserDataViewModel = viewModel()) {
    val achievements = remember { dataViewModel.state.value["achievements"] as ArrayList<Map<String,String>>  }
    val achievementsPairs = mutableListOf<Pair<String, String>>()
    achievements?.let { array ->
        for (map in array) {
            val key = map["first"]
            val value = map["second"]
            if (key != null && value != null) {
                achievementsPairs.add(key to value)
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.wall))
            Spacer(modifier = Modifier.height(50.dp))
            AchievementsList(achievements = achievementsPairs)
        }
        SystemBackButtonHandler {
            HeartStitcherRouter.navigateTo(Screen.AchievementsScreen)
        }
    }
}