package com.wtfih.heartstitcher.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.wtfih.heartstitcher.data.rules.Validator
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen

class LogInViewModel : ViewModel() {

    private val TAG = LogInViewModel::class.simpleName

    var logInUIState = mutableStateOf(LogInUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event:LogInUIEvent,context: android.content.Context? = null){
        when(event){
            is LogInUIEvent.EmailChanged ->{
                logInUIState.value = logInUIState.value.copy(
                    email = event.email
                )
            }

            is LogInUIEvent.PasswordChanged ->{
                logInUIState.value = logInUIState.value.copy(
                    password = event.password
                )
            }

            is LogInUIEvent.LoginButtonClicked ->{
                login(context = context)
            }
        }
        validateLogInDataWithRules()
    }

    private fun validateLogInDataWithRules(){
        val emailResult = Validator.validateEmail(
            email = logInUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = logInUIState.value.password
        )

        Log.d(TAG,"Inside_validateDataWithRules")
        Log.d(TAG,"emailResult = $emailResult")
        Log.d(TAG,"passwordResult = $passwordResult")

        logInUIState.value = logInUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status &&  passwordResult.status

    }

    private fun login(context: android.content.Context? = null) {

        loginInProgress.value = true

        val email = logInUIState.value.email
        val password = logInUIState.value.password
        saveCredentials(context = context!!, email = email, password = password)

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                Log.d(TAG,"Inside_login_success")
                Log.d(TAG,"${it.isSuccessful}")

                if(it.isSuccessful){
                    loginInProgress.value = false
                    HeartStitcherRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener{
                Log.d(TAG,"Inside_login_failure")
                Log.d(TAG,"${it.localizedMessage}")

                loginInProgress.value = false
            }
    }

}