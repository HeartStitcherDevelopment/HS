package com.wtfih.heartstitcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.wtfih.heartstitcher.app.HeartSitcherApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val database = Firebase.firestore
            HeartSitcherApp()
        }
    }


}
