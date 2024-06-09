package com.wtfih.heartstitcher.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.ButtonComponent
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.components.NormalTextComponent
import com.wtfih.heartstitcher.components.TextFieldComponent
import com.wtfih.heartstitcher.data.Globals
import com.wtfih.heartstitcher.data.UserDataViewModel
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler
import java.time.LocalDate

@Composable
fun WakeUpScreen(dataViewModel: UserDataViewModel = viewModel()) {
    val dreams = remember { dataViewModel.state.value["dreams"] as? MutableList<Pair<String,Pair<String,Int>>> ?: mutableListOf() }
    var textValue by remember { mutableStateOf("") }
    var type by remember { mutableStateOf(1) }
    val db = Firebase.firestore
    val id = Firebase.auth.currentUser!!.uid
    val date = remember { LocalDate.now() }
    val dateString = "${date.year}-${date.monthValue.toString().padStart(2, '0')}-${date.dayOfMonth.toString().padStart(2, '0')}"
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.good_morning))

            Spacer(modifier = Modifier.height(40.dp))

            NormalTextComponent(value = stringResource(id = R.string.morning))

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    color = Globals.ColorTheme,
                    thickness = 3.dp
                )
            }
           
           Spacer(modifier = Modifier.weight(1f))
            
            TextFieldComponent(
                value = textValue,
                onValueChange = { textValue = it },
                labelValue = stringResource(id = R.string.type_here),
                width = 400,
                initialLines = 10
            )

            Spacer(modifier = Modifier.height(20.dp))

            ButtonComponent(
                value = if(type == 1) stringResource(id = R.string.pleasent_dream)
                else if (type == 2) stringResource(id = R.string.neutral)
                else if (type == 3) stringResource(id = R.string.bad_dream)
                else if (type == 4) stringResource(id = R.string.slept_well)
                else stringResource(id = R.string.sleep_issue),
                onButtonClicked = {
                    if(type == 5)type = 1
                    else type++
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            ButtonComponent(value = stringResource(id = R.string.finish),
                onButtonClicked = {
                    dreams.add(Pair(dateString, Pair(textValue, type)))
                    db.collection("users").document(id).update("dreams", dreams)
                    Toast.makeText(
                        context,
                        "Success!",
                        Toast.LENGTH_LONG
                    ).show()
                    HeartStitcherRouter.navigateTo(Screen.HomeScreen)
                }
            )
            SystemBackButtonHandler {
                HeartStitcherRouter.navigateTo(Screen.HomeScreen)
            }
        }
    }
}