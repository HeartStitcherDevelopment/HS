package com.wtfih.heartstitcher.screens

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.data.WaterNotificationService
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationScreen() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            //.background(brush = Brush.horizontalGradient(listOf(Purple, Blue)))
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.notepad))
            val postNotificationPermission =
                rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

            val waterNotificationService = WaterNotificationService(context)

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

        }
    }
    SystemBackButtonHandler {
        HeartStitcherRouter.navigateTo(Screen.HomeScreen)
    }
}