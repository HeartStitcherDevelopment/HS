package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.components.ButtonComponent
import com.wtfih.heartstitcher.components.CheckboxComponent
import com.wtfih.heartstitcher.components.ClickableLoginTextComponent
import com.wtfih.heartstitcher.components.DividerTextComponent
import com.wtfih.heartstitcher.components.HeadingTextComponent
import com.wtfih.heartstitcher.components.NormalTextComponent
import com.wtfih.heartstitcher.components.PasswordTextField
import com.wtfih.heartstitcher.components.TextFieldComponent
import com.wtfih.heartstitcher.data.SignUpUIEvent
import com.wtfih.heartstitcher.data.SignUpViewModel
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen

@Composable
fun SignUpScreen (signUpViewModel: SignUpViewModel = viewModel()){
    Box(modifier = Modifier.fillMaxSize()
        //.background(brush = Brush.horizontalGradient(listOf(Purple, Blue)))
        .padding(28.dp),
        contentAlignment = Alignment.Center,
    ){

            Column (modifier = Modifier.fillMaxSize()
            ){
                NormalTextComponent(value = stringResource(id = R.string.hello))

                HeadingTextComponent(value = stringResource(id = R.string.create_account))

                Spacer(modifier = Modifier.height(20.dp))

                TextFieldComponent(labelValue = stringResource(id = R.string.first_name),
                    painterResource(id = R.drawable.profile),
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUIState.value.firstNameError
                )

                TextFieldComponent(labelValue = stringResource(id = R.string.last_name),
                    painterResource(id = R.drawable.profile),
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.LastNameChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUIState.value.lastNameError
                )

                TextFieldComponent(labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.email),
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.EmailChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUIState.value.emailError
                )

                PasswordTextField(
                    labelValue = stringResource(id = R.string.password),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.PasswordChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUIState.value.passwordError
                )

                CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions)
                    , onTextSelected = {
                        HeartStitcherRouter.navigateTo(Screen.TermsAndConditionsScreen)
                    },
                    onCheckedChange = {
                        signUpViewModel.onEvent(SignUpUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        signUpViewModel.onEvent(SignUpUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = signUpViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    HeartStitcherRouter.navigateTo(Screen.LoginScreen)
                })

            }


        if(signUpViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }
    }



}
