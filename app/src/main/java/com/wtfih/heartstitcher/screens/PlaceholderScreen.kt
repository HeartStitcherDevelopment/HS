package com.wtfih.heartstitcher.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.FirebaseApp
import com.wtfih.heartstitcher.data.autoLogin

@Composable
fun PlaceHolderScreen() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        FirebaseApp.initializeApp(context)
        autoLogin(context)
    }
}