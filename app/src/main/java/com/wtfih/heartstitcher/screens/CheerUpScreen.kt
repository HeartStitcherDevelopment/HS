package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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


@Composable
fun CheerUpScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.horizontalGradient(listOf(Purple, Blue)))
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.cheer))
            MyApp()
        }
        SystemBackButtonHandler {
            HeartStitcherRouter.navigateTo(Screen.HomeScreen)
        }
    }
}
@Composable
fun MyApp() {
    var notificationText by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            // Set the notification text
            notificationText = "Button clicked!"
            // Show the dialog
            showDialog = true
        }) {
            Text("Click me")
        }
    }

    // Display the notification
    if (notificationText.isNotBlank()) {
        Snackbar(
            modifier = Modifier.padding(16.dp),
            action = {
                Button(onClick = { notificationText = "" }) {
                    Text("Dismiss")
                }
            }
        ) {
            Text(text = notificationText)
        }
    }

    // Display the dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Confirmation") },
            text = { Text("Are you sure you want to proceed?") },
            confirmButton = {
                Button(onClick = {
                    // Handle confirmation action
                    notificationText = "Confirmed"
                    showDialog = false
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("No")
                }
            }
        )
    }
}