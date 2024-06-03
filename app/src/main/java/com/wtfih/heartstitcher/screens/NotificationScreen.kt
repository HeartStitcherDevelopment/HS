package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler

@Composable
fun NotificationScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            //.background(brush = Brush.horizontalGradient(listOf(Purple, Blue)))
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            /*HeadingTextComponent(value = stringResource(id = R.string.notepad))
            val postNotificationPermission =
                rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

            val waterNotificationService = WaterNotificationService(this)

            LaunchedEffect(key1 = true) {
                if (!postNotificationPermission.status.isGranted) {
                    postNotificationPermission.launchPermissionRequest()
                }
            }

            Column {
                Button(
                    onClick = {
                        waterNotificationService.showBasicNotification()
                    }
                ) {
                    Text(text = "Show basic notification")
                }

                Button(
                    onClick = {
                        waterNotificationService.showExpandableNotification()
                    }
                ) {
                    Text(text = "Show expandable with image notification")
                }

                Button(
                    onClick = {
                        waterNotificationService.showExpandableLongText()
                    }
                ) {
                    Text(text = "Show expandable with text notification")
                }

                Button(
                    onClick = {
                        waterNotificationService.showInboxStyleNotification()
                    }
                ) {
                    Text(text = "Show inbox-style notification")
                }

                Button(
                    onClick = {
                        waterNotificationService.showNotificationGroup()
                    }
                ) {
                    Text(text = "Show inbox-style notification group")
                }
            }
            */
        }
    }
    SystemBackButtonHandler {
        HeartStitcherRouter.navigateTo(Screen.HomeScreen)
    }
}