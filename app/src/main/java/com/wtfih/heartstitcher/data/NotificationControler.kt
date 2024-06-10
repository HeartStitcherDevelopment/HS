package com.wtfih.heartstitcher.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.time.LocalTime

@Composable
fun CheckAndRunAction(hour: Int, min: Int, isAm: Boolean, flag: Boolean, currentTime: LocalTime, flager: Int) {
    val context = LocalContext.current
    val waterNotificationService = WaterNotificationService(context)
    if (!flag) return

    // Convert hour to 24-hour format
    val convertedHour = if (isAm) {
        if (hour == 12) 0 else hour
    } else {
        if (hour == 12) 12 else hour + 12
    }

    // Get the current time

    val convertedTime = LocalTime.of(convertedHour, min)

    // Check if the current time matches the converted time
    if (currentTime.hour == convertedHour && currentTime.minute == min) {
        waterNotificationService.A()
    }
}