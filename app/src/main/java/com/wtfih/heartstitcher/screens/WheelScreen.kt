package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.NumberPicker
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.components.LargeTextField
import com.wtfih.heartstitcher.components.SpinButtonComponent
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
fun WheelScreen() {
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
                List(8) { index ->
                    val colors = if (index % 2 == 0) colors1 else colors2

                    SpinWheelItem(
                        colors = colors.toPersistentList()
                    ) {
                        Text(
                            text = "$$index",
                            style = TextStyle(color = Color(0xFF4CAF50), fontSize = 20.sp)
                        )
                    }

                }.toPersistentList()
            }
            var pickerValue by remember { mutableIntStateOf(1) }

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

                    HeadingTextComponent(value = stringResource(id = R.string.wheel))

                    Spacer(modifier = Modifier.height(50.dp))

                    Column(modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally){
                    Box(modifier = Modifier.size(250.dp)) {
                        SpinWheelComponent(spinState)
                    }
                    Spacer(modifier = Modifier.size(60.dp))
                    /*SmallButtonComponent(
                        value = stringResource(id = R.string.center),
                        onButtonClicked = { spinState.goto(pickerValue) },
                        isEnabled = true
                    )
                            Spacer(modifier = Modifier.width(30.dp))
                    SmallButtonComponent(
                        value = stringResource(id = R.string.spin),
                        onButtonClicked = { spinState.launchInfinite() },
                        isEnabled = true,
                        width = 84
                    )
                            Spacer(modifier = Modifier.width(30.dp))*/
                    SpinButtonComponent(
                        onButtonClicked = { spinState.stoppingWheel(pickerValue) }
                    )}

                    Spacer(modifier = Modifier.size(10.dp))
                    LargeTextField(onStringListChange = {}, labelValue = "")
                NumberPicker(
                    value = pickerValue,
                    range = items.indices,
                    onValueChange = {
                        pickerValue = it
                    }
                )

            }
        }
        SystemBackButtonHandler {
            HeartStitcherRouter.navigateTo(Screen.HomeScreen)
        }
}
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