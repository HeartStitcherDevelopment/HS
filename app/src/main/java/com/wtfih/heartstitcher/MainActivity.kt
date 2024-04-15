package com.wtfih.heartstitcher

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
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

private fun checkCurrentUser() {
    // [START check_current_user]
    val user = Firebase.auth.currentUser
    if (user != null) {
        // User is signed in
    } else {
        // No user is signed in
    }
    // [END check_current_user]
}

private fun getUserProfile() {
    // [START get_user_profile]
    val user = Firebase.auth.currentUser
    user?.let {
        // Name, email address, and profile photo Url
        val name = it.displayName
        val email = it.email
        val photoUrl = it.photoUrl

        // Check if user's email is verified
        val emailVerified = it.isEmailVerified

        // The user's ID, unique to the Firebase project. Do NOT use this value to
        // authenticate with your backend server, if you have one. Use
        // FirebaseUser.getIdToken() instead.
        val uid = it.uid
    }
    // [END get_user_profile]
}
@Composable
fun IButtons(active: Boolean = true, ocmess: String = "Clicked on Icon Button"){
    val context = LocalContext.current
    IconButton(onClick = { Toast.makeText(context, ocmess, Toast.LENGTH_SHORT).show()},enabled = active){
        Icon(
            Icons.Filled.Refresh, contentDescription = "Refresh Button"
            ,tint = Color.DarkGray,modifier = Modifier.size(40.dp))

    }

}

@Composable
fun TButtons(text: String = "N/A", active: Boolean = true, ocmess: String = "Clicked on Text Button"){
    val context = LocalContext.current
    TextButton(onClick = { Toast.makeText(context, ocmess, Toast.LENGTH_SHORT).show()},enabled = active){
        Text(text = text)

    }
}

@Composable
fun OButtons(text: String = "N/A", active: Boolean = true, ocmess: String = "Clicked on Outlined Button"){
    val context = LocalContext.current
    OutlinedButton(onClick = { Toast.makeText(context, ocmess, Toast.LENGTH_SHORT).show()},enabled = active){
        Text(text = text)

    }
}

@Composable
fun Buttons(text: String = "N/A", active: Boolean = true, ocmess: String = "Clicked on Button", pad: Int = 10, bor: Int = 3, bro: Int = 100){
    val context = LocalContext.current
    Button(onClick = { Toast.makeText(context, ocmess,
        Toast.LENGTH_SHORT).show()}, shape = CircleShape,enabled = active,
        contentPadding = PaddingValues(pad.dp),
        border = BorderStroke(bor.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.Magenta,
            contentColor = Color.DarkGray
        ),modifier = Modifier.widthIn(max = bro.dp) // Set the maximum width of the button
    ){
        Text(text = text,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(6.dp))

    }
}

@Composable
fun Testing_Button(text: String = "Test") {
    var adText by remember {
        mutableStateOf(text)
    }
    Button(onClick = { adText = "HELP" }) {
        Text(text = adText)
    }
}



/*HeartStitcherTheme {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(42.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                //.fillMaxSize()

                .padding(10.dp)
        ) {
            Button(onClick = {
                startActivity(Intent(applicationContext,TestingActivity::class.java))
            }, shape = CircleShape,enabled = true,
                contentPadding = PaddingValues(10.dp),
                border = BorderStroke(3.dp, Color.Black),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Magenta,
                    contentColor = Color.DarkGray
                ),modifier = Modifier.widthIn(max = 500.dp) // Set the maximum width of the button
            ){

            }
            Buttons(text = "PANIC",ocmess = "PANIC", bro = 500)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier//.fillMaxSize()

            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(69.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        //.fillMaxSize()
                        .padding(10.dp)
                ) {
                    Buttons(text = "Test button", ocmess = "UP", pad = 10)
                    OButtons(text = "Test button", ocmess = "MID")
                    Testing_Button("I need")
                    Buttons(text = "ENABLE ALL", active = false, ocmess = "WIP for now ;-;")
                    IButtons(ocmess = "REFRESHED")
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(69.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        //.fillMaxSize()

                        .padding(10.dp)
                ) {
                    Buttons(text = "Test button", ocmess = "UP", pad = 10)
                    OButtons(text = "Test button", ocmess = "MID")
                    TButtons(text = "Test button", ocmess = "DOWN")
                    Buttons(text = "DISABLE ALL", active = false, ocmess = "WIP for now ;-;")
                    IButtons(ocmess = "REFRESHED")
                }
            }
        }

    }
}
*/
