package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.components.SpinText
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler
import com.wtfih.heartstitcher.ui.theme.Blue
import com.wtfih.heartstitcher.ui.theme.Purple

@Composable
fun SpinWheelDataScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.horizontalGradient(listOf(Purple, Blue)))
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.wip))
            Spacer(modifier = Modifier.height(30.dp))
            SpinText()
        }
        SystemBackButtonHandler {
            HeartStitcherRouter.navigateTo(Screen.HomeScreen)
        }
    }
}