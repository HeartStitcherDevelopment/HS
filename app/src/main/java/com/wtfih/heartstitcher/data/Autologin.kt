package com.wtfih.heartstitcher.data

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen

fun saveCredentials(context: Context, email: String, password: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("email", email)
        putString("password", password)
        apply()
    }
}

fun getCredentials(context: Context): Pair<String?, String?> {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val email = sharedPreferences.getString("email", null)
    val password = sharedPreferences.getString("password", null)
    return Pair(email, password)
}

fun autoLogin(context: Context) {
    val (email, password) = getCredentials(context)
    if (email != null && password != null) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    HeartStitcherRouter.navigateTo(Screen.HomeScreen)
                } else {
                    HeartStitcherRouter.navigateTo(Screen.LoginScreen)
                }
            }
    } else {
        HeartStitcherRouter.navigateTo(Screen.SignUpScreen)
    }
}