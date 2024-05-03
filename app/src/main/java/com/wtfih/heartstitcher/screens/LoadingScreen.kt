package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler
import com.wtfih.heartstitcher.ui.theme.Blue
import com.wtfih.heartstitcher.ui.theme.Purple
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(
    loadingTimeMillis: Long = 3000, // Default loading time in milliseconds
    onLoadingComplete: () -> Unit // Callback function to execute after loading
) {
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        delay(loadingTimeMillis)
        isLoading = false
        onLoadingComplete() // Execute the given task after loading
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.horizontalGradient(listOf(Purple, Blue)))
                .padding(28.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeadingTextComponent(value = stringResource(id = R.string.loading))
                Spacer(modifier = Modifier.height(50.dp))
                // You can add additional loading components here
            }
        }
    } else {
        // Once loading is complete, you can navigate to HomeScreen or perform other actions
        onLoadingComplete.invoke()
    }
    SystemBackButtonHandler {
        HeartStitcherRouter.navigateTo(Screen.HomeScreen)
    }
}