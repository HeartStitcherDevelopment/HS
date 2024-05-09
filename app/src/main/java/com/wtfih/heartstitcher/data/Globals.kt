package com.wtfih.heartstitcher.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.ui.theme.Login1
import com.wtfih.heartstitcher.ui.theme.Login2
import com.wtfih.heartstitcher.ui.theme.LoginTheme

object Globals {
    var taskFlag: Boolean = false
    var wheelFlag: Boolean = false
    var ColorTheme: Color = LoginTheme
    var ButtonColor1: Color = Login1
    var ButtonColor2: Color = Login2
    var Font: Font = Font(R.font.default_font)
}