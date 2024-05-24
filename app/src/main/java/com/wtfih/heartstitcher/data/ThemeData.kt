package com.wtfih.heartstitcher.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.font.Font
import androidx.lifecycle.ViewModel
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.ui.theme.Rain1
import com.wtfih.heartstitcher.ui.theme.Rain2
import com.wtfih.heartstitcher.ui.theme.RainTheme

class ThemeData : ViewModel() {
    private val _theme = mutableIntStateOf(-1)
    private val _music = mutableStateOf(true)
    private val _sounds = mutableStateOf(true)
    private val _login = mutableStateOf(false)
    private val _color = mutableStateOf(value = RainTheme)
    private val _color1 = mutableStateOf(value = Rain1)
    private val _color2 = mutableStateOf(value = Rain2)
    private val _font = mutableStateOf(value = Font(R.font.default_font))
    val theme: State<Int> = _theme
    val music: State<Boolean> = _music
    val sounds: State<Boolean> = _sounds
    val login: State<Boolean> = _login
    val color: MutableState<androidx.compose.ui.graphics.Color> = _color
    val color1: MutableState<androidx.compose.ui.graphics.Color> = _color1
    val color2: MutableState<androidx.compose.ui.graphics.Color> = _color2
    val font: State<Font> = _font

    fun setTheme(newTheme: Int) {
        _theme.value = newTheme
    }
    fun setMusic(){
        _music.value = !_music.value
    }
    fun setSounds(){
        _sounds.value = !_sounds.value
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
    fun logOutFlag(){
        _login.value = false
    }
    fun setFont(newFont: Font){
        _font.value = newFont
    }
}