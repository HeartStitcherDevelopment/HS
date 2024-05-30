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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.components.NormalTextComponent
import com.wtfih.heartstitcher.components.SmallButtonComponent
import com.wtfih.heartstitcher.data.Globals
import com.wtfih.heartstitcher.data.Globals.ColorTheme
import com.wtfih.heartstitcher.data.Globals.Font
import com.wtfih.heartstitcher.data.UserDataViewModel
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler


@Composable
fun NutritionScreen(dataViewModel: UserDataViewModel = viewModel()) {
    val db = Firebase.firestore
    val id = Firebase.auth.currentUser!!.uid
    var waterF : Boolean =  remember { dataViewModel.state.value["water"] as? Boolean  ?: false }
    var water by remember { mutableStateOf(waterF) }
    var foodF : Boolean =  remember { dataViewModel.state.value["food"] as? Boolean  ?: false }
    var food by remember { mutableStateOf(foodF) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.nutrition))

            Spacer(modifier = Modifier.height(20.dp))

            NormalTextComponent(value = stringResource(id = R.string.nutrition_info))

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

            Spacer(modifier = Modifier.height(20.dp))

            NormalTextComponent(value = stringResource(id = R.string.nutrition_rules))

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Reminders",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 25.sp,
                    color = ColorTheme,
                    fontFamily = Font.toFontFamily()
                )

                Spacer(modifier = Modifier.width(5.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    color = ColorTheme,
                    thickness = 3.dp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){

                Text(text = stringResource(id = R.string.food_reminder),
                    modifier = Modifier.padding(8.dp),
                    fontSize = 25.sp,
                    color = ColorTheme,
                    fontFamily = Font.toFontFamily()
                )

                Spacer(modifier = Modifier.width(30.dp))

                SmallButtonComponent(value = if(!food) "Off" else "On", onButtonClicked = {
                    food = !food
                    db.collection("users").document(id).update("food", food)
                })
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){

                Text(text = stringResource(id = R.string.water_reminder),
                    modifier = Modifier.padding(8.dp),
                    fontSize = 25.sp,
                    color = ColorTheme,
                    fontFamily = Font.toFontFamily()
                )

                Spacer(modifier = Modifier.width(30.dp))

                SmallButtonComponent(value = if(!water) "Off" else "On", onButtonClicked = {
                    water = !water
                    db.collection("users").document(id).update("water", water)
                })
            }

        }
        SystemBackButtonHandler {
            HeartStitcherRouter.navigateTo(Screen.HomeScreen)
        }
    }
}