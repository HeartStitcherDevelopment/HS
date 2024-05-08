package com.wtfih.heartstitcher.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.wtfih.heartstitcher.ui.theme.Default1
import com.wtfih.heartstitcher.ui.theme.Default2
import com.wtfih.heartstitcher.ui.theme.DefaultTheme

class ThemeData : ViewModel() {
    private val _theme = mutableIntStateOf(-1)
    private val _login = mutableStateOf(false)
    private val _color = mutableStateOf(DefaultTheme)
    private val _color1 = mutableStateOf(Default1)
    private val _color2 = mutableStateOf(Default2)
    val theme: State<Int> = _theme
    val login: State<Boolean> = _login
    val color: MutableState<androidx.compose.ui.graphics.Color> = _color
    val color1: MutableState<androidx.compose.ui.graphics.Color> = _color1
    val color2: MutableState<androidx.compose.ui.graphics.Color> = _color2

    fun setTheme(newTheme: Int) {
        _theme.value = newTheme
    }
    fun LogInFLag() {
        _login.value = true
    }
    fun setColor(newColor: androidx.compose.ui.graphics.Color){
        _color.value = newColor
    }

    fun setColor1(newColor: androidx.compose.ui.graphics.Color){
        _color1.value = newColor
    }
    fun setColor2(newColor: androidx.compose.ui.graphics.Color){
        _color2.value = newColor
    }
    fun LogOutFlag(){
        _login.value = false
    }
}