package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.components.Notepad
import com.wtfih.heartstitcher.data.UserDataViewModel
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler

@Composable
fun BrowseNotesScreen(dataViewModel: UserDataViewModel = viewModel()) {
    val notes = remember { dataViewModel.state.value["notes"] as ArrayList<Map<String, Map<String, String>>> ?: arrayListOf() }
    val notesPair = mutableListOf<Pair<String, Pair<String, String>>>()

    notes.let { array ->
        for (map in array) {
            val date = map["first"] as String
            val stringMap = map["second"] as Map<String, String>
            val title = stringMap["first"]!!
            val text = stringMap["second"]!!
            notesPair.add(Pair(date, Pair(title, text)))
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.notes))

            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Notepad(notes = notesPair)
            }

            SystemBackButtonHandler {
                HeartStitcherRouter.navigateTo(Screen.NotepadScreen)
            }
        }
    }
}