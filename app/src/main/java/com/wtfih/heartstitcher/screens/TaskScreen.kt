package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.wtfih.heartstitcher.components.TaskText
import com.wtfih.heartstitcher.data.Globals.taskFlag
import com.wtfih.heartstitcher.data.UserDataViewModel
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler
import com.wtfih.heartstitcher.ui.theme.Blue
import com.wtfih.heartstitcher.ui.theme.Purple


@Composable
fun TaskScreen(dataViewModel: UserDataViewModel = viewModel()) {
    if(taskFlag){
        dataViewModel.refresh()
    }
    val tasks = remember { dataViewModel.state.value["tasks"] as? MutableList<String> ?: mutableListOf() }
    val db = Firebase.firestore
    val id = Firebase.auth.currentUser!!.uid
    if(!taskFlag) {
        taskFlag = true
        HeartStitcherRouter.navigateTo(Screen.LoadingScreen)
    }
    else{
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
                        val tasksList = tasks.map { it.toString() }
                        db.collection("users").document(id).update("tasks", tasksList)
                    })
                }
            }
            SystemBackButtonHandler {
                HeartStitcherRouter.navigateTo(Screen.HomeScreen)
            }

    }}
}

/*
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




@

*/