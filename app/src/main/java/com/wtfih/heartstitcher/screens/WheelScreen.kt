package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.SpinButtonComponent
import com.wtfih.heartstitcher.data.Globals
import com.wtfih.heartstitcher.data.UserDataViewModel
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler
import com.wtfih.heartstitcher.spin_wheel.ExampleUnitTest.toColor
import com.wtfih.heartstitcher.spin_wheel.SpinWheelComponent
import com.wtfih.heartstitcher.spin_wheel.SpinWheelItem
import com.wtfih.heartstitcher.spin_wheel.rememberSpinWheelState
import com.wtfih.heartstitcher.ui.theme.Blue
import com.wtfih.heartstitcher.ui.theme.Purple
import kotlinx.collections.immutable.toPersistentList
import kotlin.random.Random


@Composable
fun WheelScreen(dataViewModel: UserDataViewModel = viewModel()) {
        val tasks =
        remember { dataViewModel.state.value["tasks"] as? MutableList<String> ?: mutableListOf() }
    if (!Globals.taskFlag) {
        Globals.taskFlag = true
        HeartStitcherRouter.navigateTo(Screen.LoadingScreen2)}
        else{
            val colors1 = remember {
                listOf(
                    "380048",
                    "2B003D",
                    "40004A",
                    "590058",
                    "730067"
                ).map { it.toColor() }
            }
            val colors2 = remember {
                listOf(
                    "F9A114",
                    "FD7D1B",
                    "F9901A",
                    "F6A019",
                    "EFC017"
                ).map { it.toColor() }
            }
            val items = remember {
                List(tasks.size) { index ->
                    val colors = if (index % 2 == 0) colors1 else colors2
                    val element = tasks.getOrNull(index) ?: ""
                    SpinWheelItem(
                        colors = colors.toPersistentList()
                    ) {
                        Text(
                            text = if (element.length >9) {
                                "           ${element.take(9)}..."
                            } else {
                                "         $element"},
                            modifier = Modifier
                                .graphicsLayer {
                                    rotationZ = 90f
                                },
                            style = TextStyle(color = Color(0xFFFFFFFF), fontSize = 20.sp),
                            textAlign = TextAlign.End
                        )
                    }

                }.toPersistentList()
            }
            var current by remember { mutableIntStateOf(0) }

            val spinState = rememberSpinWheelState(
                items = items,
                backgroundImage = R.drawable.spin_wheel_background,
                centerImage = R.drawable.spin_wheel_center,
                indicatorImage = R.drawable.spin_wheel_tick,
                onSpinningFinished = null,
            )
        Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.horizontalGradient(listOf(Purple, Blue)))
            .padding(28.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                    Column(modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally){
                    Box(modifier = Modifier.size(400.dp)) {
                        SpinWheelComponent(spinState)
                    }
                        Spacer(modifier = Modifier.size(110.dp))
                    SpinButtonComponent(result = tasks[current],
                        onButtonClicked = {current = (0 until tasks.size).random();
                            spinState.stoppingWheel(current)}
                    )}

            }
        }
        SystemBackButtonHandler {
            HeartStitcherRouter.navigateTo(Screen.TaskScreen)
        }
}}
fun getDegreeFromSection(items: List<SpinWheelItem>, section: Int): Float {
    val pieDegree = 360f / items.size
    return pieDegree * section.times(-1)
}

fun getDegreeFromSectionWithRandom(items: List<SpinWheelItem>, section: Int): Float {
    val pieDegree = 360f / items.size
    val exactDegree = pieDegree * section.times(-1)

    val pieReduced = pieDegree * 0.9f //to avoid stop near border
    val multiplier = if (Random.nextBoolean()) 1f else -1f //before or after exact degree
    val randomDegrees = Random.nextDouble(0.0, pieReduced / 2.0)
    return exactDegree + (randomDegrees.toFloat() * multiplier)
}