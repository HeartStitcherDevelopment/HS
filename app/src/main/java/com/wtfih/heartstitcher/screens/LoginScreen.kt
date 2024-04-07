package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.ButtonComponent
import com.wtfih.heartstitcher.components.ClickableLoginTextComponent
import com.wtfih.heartstitcher.components.DividerTextComponent
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.components.NormalTextComponent
import com.wtfih.heartstitcher.components.PasswordTextField
import com.wtfih.heartstitcher.components.TextField
import com.wtfih.heartstitcher.components.UnderLinedTextComponent
import com.wtfih.heartstitcher.data.LogInUIEvent
import com.wtfih.heartstitcher.data.LogInViewModel
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.navigation.SystemBackButtonHandler
import com.wtfih.heartstitcher.ui.theme.Blue
import com.wtfih.heartstitcher.ui.theme.Purple

@Composable
fun LoginScreen(logInViewModel: LogInViewModel = viewModel()){

    Box(modifier = Modifier.fillMaxSize()
                            .background(
            brush = Brush.horizontalGradient(listOf(Purple, Blue)))
                            .padding(28.dp),
        contentAlignment = Alignment.Center,
        ) {


            Column(
                modifier = Modifier.fillMaxSize()
                                    .background(Color.Transparent)
            ) {
                NormalTextComponent(value = stringResource(id = R.string.hello))

                HeadingTextComponent(value = stringResource(id = R.string.welcome))

                Spacer(modifier = Modifier.height(20.dp))

                TextField(labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.email),
                    onTextSelected = {
                        logInViewModel.onEvent(LogInUIEvent.EmailChanged(it))
                    },
                    errorStatus = logInViewModel.logInUIState.value.emailError

                )

                PasswordTextField(
                    labelValue = stringResource(id = R.string.password),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        logInViewModel.onEvent(LogInUIEvent.PasswordChanged(it))
                    },
                    errorStatus = logInViewModel.logInUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))

                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        logInViewModel.onEvent(LogInUIEvent.LoginButtonClicked)
                    },
                    isEnabled = logInViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(40.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    HeartStitcherRouter.navigateTo(Screen.SignUpScreen)
                })
            }

        if(logInViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
    }
    SystemBackButtonHandler {
        HeartStitcherRouter.navigateTo(Screen.SignUpScreen)
    }
}


@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}