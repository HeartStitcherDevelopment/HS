package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chargemap.compose.numberpicker.NumberPicker
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.R.string
import com.wtfih.heartstitcher.components.ButtonComponent
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.components.NormalTextComponent
import com.wtfih.heartstitcher.components.SmallButtonComponent
import com.wtfih.heartstitcher.data.Globals.ColorTheme
import com.wtfih.heartstitcher.data.Globals.Font
import com.wtfih.heartstitcher.data.UserDataViewModel
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler


@Composable
fun SleepScreen(dataViewModel: UserDataViewModel = viewModel()) {
    val db = Firebase.firestore
    val id = Firebase.auth.currentUser!!.uid
    var selectedHourF : Int =  remember { dataViewModel.state.value["sleep1"] as? Long ?: 0 }.toInt()
    var selectedMinuteF :Int =  remember { dataViewModel.state.value["sleep2"] as? Long ?: 0 }.toInt()
    var isAMF : Boolean =  remember { dataViewModel.state.value["sleep3"] as? Boolean  ?: true }
    var isNotificationF : Boolean =  remember { dataViewModel.state.value["sleep"] as? Boolean  ?: false}
    var selectedHour by remember { mutableStateOf(selectedHourF.toInt()) }
    var selectedMinute by remember { mutableStateOf(selectedMinuteF.toInt()) }
    var isAM by remember { mutableStateOf(isAMF) }
    var isNotification by remember { mutableStateOf(isNotificationF) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            HeadingTextComponent(value = stringResource(id = string.sleep))

            Spacer(modifier = Modifier.height(20.dp))

            NormalTextComponent(value = stringResource(id = string.sleep_exp))

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    color = ColorTheme,
                    thickness = 3.dp
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            NormalTextComponent(value = stringResource(id = string.sleep_rules))

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly) {

                NumberPicker(
                    value = selectedHour,
                    range = (0..12).toList(),
                    onValueChange = { selectedHour = it },
                    dividersColor = ColorTheme,
                    textStyle = TextStyle(
                        fontSize = 30.sp,
                        color = ColorTheme,
                        fontFamily = Font.toFontFamily()
                    )
                )

                Text(text = ":", color = ColorTheme, fontFamily = Font.toFontFamily(), fontSize = 30.sp)

                NumberPicker(
                    value = selectedMinute,
                    range = (0..59).toList(),
                    onValueChange = { selectedMinute = it },
                    dividersColor = ColorTheme,
                    textStyle = TextStyle(
                        fontSize = 30.sp,
                        color = ColorTheme,
                        fontFamily = Font.toFontFamily()
                    )
                )

                Text(text = "", color = ColorTheme, fontFamily = Font.toFontFamily(), fontSize = 30.sp)

                SmallButtonComponent(value = if(isAM) "AM" else "PM", onButtonClicked = { isAM = !isAM })
            }

            Spacer(modifier = Modifier.height(20.dp))

            ButtonComponent(value = if(!isNotification) stringResource(id = R.string.set)
                else stringResource(id = R.string.unset),
                onButtonClicked = {
                    isNotification = !isNotification
                    db.collection("users").document(id).update("sleep1", selectedHour)
                    db.collection("users").document(id).update("sleep2", selectedMinute)
                    db.collection("users").document(id).update("sleep3", isAM)
                    db.collection("users").document(id).update("sleep", isNotification)
                })

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    color = ColorTheme,
                    thickness = 3.dp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            ButtonComponent(value = stringResource(id = string.browse),
                onButtonClicked = { HeartStitcherRouter.navigateTo(Screen.BrowseDreamsScreen) })

            Spacer(modifier = Modifier.weight(1f))
        }
        SystemBackButtonHandler {
            HeartStitcherRouter.navigateTo(Screen.HomeScreen)
        }
    }
}

