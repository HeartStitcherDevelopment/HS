package com.wtfih.heartstitcher.screens

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.AchievementPreview
import com.wtfih.heartstitcher.components.ButtonComponent
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.data.UserDataViewModel
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler
import java.io.File
import java.io.IOException

@Composable
fun AddAchievementScreen(dataViewModel: UserDataViewModel = viewModel()) {
    val context = LocalContext.current
    val achievements = remember { dataViewModel.state.value["achievements"] as? MutableList<Pair<String,String>> ?: mutableListOf() }
    val id = Firebase.auth.currentUser!!.uid
    val storageReference = Firebase.storage.reference
    val imageRef = storageReference.child("users/$id")
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap : Bitmap? = null
    var textValue by remember { mutableStateOf("") }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedImageUri = uri
        }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            HeadingTextComponent(value = stringResource(id = R.string.create))
            Spacer(modifier = Modifier.height(30.dp))
            if (selectedImageUri != null) {
                bitmap = context.contentResolver.loadBitmap(selectedImageUri!!)
            }
            Spacer(modifier = Modifier.height(40.dp))
            AchievementPreview(value = textValue, onIconClicked = { launcher.launch("image/*") }, bitmap = bitmap,
                onValueChange =  { textValue = it }, labelValue = stringResource(id = R.string.type_here))
            Spacer(modifier = Modifier.height(60.dp))
            ButtonComponent(value = stringResource(id = R.string.finish), onButtonClicked = {
                if (bitmap != null && selectedImageUri != null) {
                    val file = File(selectedImageUri!!.path!!)
                    val fileUri = Uri.fromFile(file)
                    imageRef.putFile(fileUri).addOnSuccessListener {
                        imageRef.downloadUrl.addOnSuccessListener { uri ->
                            achievements.add(Pair(textValue, uri.toString()))
                            dataViewModel.refresh()
                        }
                    }
                }
                else if (bitmap == null && textValue.isNotEmpty()){
                    achievements.add(Pair(textValue, ""))
                    dataViewModel.refresh()
                }
            })
        }
        SystemBackButtonHandler {
            HeartStitcherRouter.navigateTo(Screen.AchievementsScreen)
        }
    }
}

fun ContentResolver.loadBitmap(uri: Uri): Bitmap? {
    return try {
        openInputStream(uri)?.use { inputStream ->
            BitmapFactory.decodeStream(inputStream)
        }
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}