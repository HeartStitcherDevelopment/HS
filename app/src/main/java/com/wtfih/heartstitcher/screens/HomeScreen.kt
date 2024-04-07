package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.ButtonComponent
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.data.SignUpViewModel

@Composable
fun HomeScreen(signUpViewModel: SignUpViewModel = SignUpViewModel()){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    )  {
        Column (modifier = Modifier.fillMaxSize()
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.home))

            ButtonComponent(value = stringResource(id = R.string.logout), onButtonClicked = {
                signUpViewModel.logout()
            },
                isEnabled = true)
        }
    }
}