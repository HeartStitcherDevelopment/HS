package com.wtfih.heartstitcher.screens

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.R.string
import com.wtfih.heartstitcher.components.AchievementComponent
import com.wtfih.heartstitcher.components.AchievementPreview
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler


@Composable
fun SleepScreen() {
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap : Bitmap? = null
    var textValue by remember { mutableStateOf("") }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedImageUri = uri
        }
    if (selectedImageUri != null) {
        bitmap = context.contentResolver.loadBitmap(selectedImageUri!!)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HeadingTextComponent(value = stringResource(id = string.sleep))

            AchievementPreview(value = textValue, onIconClicked = { launcher.launch("image/*") }, bitmap = bitmap,
                onValueChange =  { textValue = it }, labelValue = stringResource(id = string.type_here))
            Spacer(modifier = Modifier.height(30.dp))

            Row {
                AchievementComponent(value = stringResource(id = R.string.test_ach), bitmap = bitmap)
                Spacer(modifier = Modifier.width(45.dp))
                AchievementComponent(value = "testing some things ig", bitmap = bitmap)

            }
        }
        SystemBackButtonHandler {
            HeartStitcherRouter.navigateTo(Screen.HomeScreen)
        }
    }
}

