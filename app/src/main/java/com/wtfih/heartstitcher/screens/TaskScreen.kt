package com.wtfih.heartstitcher.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.components.LazyColumnItems
import com.wtfih.heartstitcher.components.MusicPlayer
import com.wtfih.heartstitcher.components.TaskText
import com.wtfih.heartstitcher.data.Globals.taskFlag
import com.wtfih.heartstitcher.data.UserDataViewModel
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler
import com.wtfih.heartstitcher.ui.theme.Blue
import com.wtfih.heartstitcher.ui.theme.Purple
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TaskScreen(dataViewModel: UserDataViewModel = viewModel()) {
    val tasks =
        remember { dataViewModel.state.value["tasks"] as? MutableList<String> ?: mutableListOf() }
    val db = Firebase.firestore
    val id = Firebase.auth.currentUser!!.uid
    var soundeffect by remember { mutableStateOf(false) }
    if (!taskFlag) {
        taskFlag = true
        HeartStitcherRouter.navigateTo(Screen.LoadingScreen)
    } else {
        taskFlag = false
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.horizontalGradient(listOf(Purple, Blue)))
                .padding(28.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            )
            {
                HeadingTextComponent(value = stringResource(id = R.string.task))
                Spacer(modifier = Modifier.height(30.dp))

                TaskText(tasks = tasks)
                if (tasks.isNotEmpty()) {
                    LazyColumnItems(items = tasks, onDeleteClicked = { item ->
                        (tasks).remove(item)
                        soundeffect = true
                        val tasksList = tasks.map { it.toString() }
                        db.collection("users").document(id).update("tasks", tasksList)
                    })
                }
            }
            if(soundeffect){
                MusicPlayer(audioResourceId = R.raw.delete)
                CoroutineScope(Dispatchers.Main).launch {
                    delay(3000)
                    soundeffect = false
                }
            }
            SystemBackButtonHandler {
                HeartStitcherRouter.navigateTo(Screen.HomeScreen)
            }
        }
    }
}
