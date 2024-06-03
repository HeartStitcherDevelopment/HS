package com.wtfih.heartstitcher.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wtfih.heartstitcher.data.rules.Validator
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import java.time.LocalDate

class SignUpViewModel : ViewModel() {

    private val TAG = SignUpViewModel::class.simpleName

    var registrationUIState = mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignUpUIEvent, context: android.content.Context? = null) {
        validateDataWithRules()
        when(event){
            is SignUpUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                val fNameResult = Validator.validateFirstName(
                    fName = registrationUIState.value.firstName
                )

                printState()
            }

            is SignUpUIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is SignUpUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()
            }

            is SignUpUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()
            }

            is SignUpUIEvent.RegisterButtonClicked -> {
                signUp(context = context)
            }

            is SignUpUIEvent.PrivacyPolicyCheckBoxClicked -> {
                registrationUIState.value = registrationUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
        }
    }

    private fun signUp(context: android.content.Context? = null) {
        Log.d(TAG,"Inside_signUp")
        printState()
        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password,
            name = registrationUIState.value.firstName,
            surname = registrationUIState.value.lastName,
            context = context
        )
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
                statusValue = registrationUIState.value.privacyPolicyAccepted
                )

        Log.d(TAG,"Inside_validateDataWithRules")
        Log.d(TAG,"fNameResult = $fNameResult")
        Log.d(TAG,"lNameResult = $lNameResult")
        Log.d(TAG,"emailResult = $emailResult")
        Log.d(TAG,"passwordResult = $passwordResult")
        Log.d(TAG,"privacyPolicyResult = $privacyPolicyResult")

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyAccepted = privacyPolicyResult.status

        )

        allValidationsPassed.value = fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status && privacyPolicyResult.status
    }

    private fun printState(){
        Log.d(TAG,"Inside_printState")
        Log.d(TAG,registrationUIState.value.toString())
    }

    private fun createUserInFirebase(email:String,password:String,name:String,surname:String, context: android.content.Context? = null){

        signUpInProgress.value = true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                Log.d(TAG,"Inside OnCompleteListener")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")

                signUpInProgress.value = false
                if(it.isSuccessful){
                    val uid = Firebase.auth.currentUser!!.uid
                    val database = Firebase.firestore
                    val user = hashMapOf(
                        "first" to name,
                        "last" to surname,
                        "mail" to email,
                        "tasks" to mutableListOf<String>(),
                        "theme" to 0,
                        "achievements" to mutableListOf<Pair<String, String>>(),
                        "music" to true,
                        "sounds" to true,
                        "sleep" to false,
                        "sleep1" to 0,
                        "sleep2" to 0,
                        "sleep3" to true,
                        "water" to false,
                        "food" to false,
                        "wake1" to 0,
                        "wake2" to true,
                        "asleep1" to 0,
                        "asleep2" to true,
                        "dreams" to mutableListOf<Pair<LocalDate,Pair<String,Int>>>()
                    )
                    saveCredentials(context = context!!, email = email, password = password)
                    database.collection("users")
                        .document(uid).set(user)
                    HeartStitcherRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener {
                Log.d(TAG,"Inside OnFailureListener")
                Log.d(TAG, "Exception = ${it.message}")
                Log.d(TAG, "Exception = ${it.localizedMessage}")
            }
    }

    fun logout(){
        var firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        val authStateListener = AuthStateListener{
            if(it.currentUser == null){
                Log.d(TAG, "Inside sign out success")
                HeartStitcherRouter.navigateTo(Screen.LoginScreen)
            }else{
                Log.d(TAG, "Inside sign out is not complete")

            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)
    }
}