@file:OptIn(ExperimentalMaterial3Api::class)

package com.wtfih.heartstitcher.components

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.data.CheerupsDataViewModel
import com.wtfih.heartstitcher.data.Globals.ButtonColor1
import com.wtfih.heartstitcher.data.Globals.ButtonColor2
import com.wtfih.heartstitcher.data.Globals.ColorTheme
import com.wtfih.heartstitcher.data.Globals.Font
import com.wtfih.heartstitcher.navigation.HeartStitcherRouter
import com.wtfih.heartstitcher.navigation.Screen
import com.wtfih.heartstitcher.ui.theme.BGColor
import com.wtfih.heartstitcher.ui.theme.PanicColor1
import com.wtfih.heartstitcher.ui.theme.Primary
import com.wtfih.heartstitcher.ui.theme.Secondary
import com.wtfih.heartstitcher.ui.theme.componentShapes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun NormalTextComponent(value:String, fontResource: Font = Font){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
        ,color = ColorTheme,
        textAlign = TextAlign.Center,
        fontFamily = fontResource.toFontFamily()
    )
}

@Composable
fun HeadingTextComponent(value:String, c: Color = ColorTheme, fontResource: Font = Font){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        )
        ,color = c,
        textAlign = TextAlign.Center,
        fontFamily = fontResource.toFontFamily()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(labelValue: String, painterResource: Painter,
              onTextSelected: (String) -> Unit,
              errorStatus:Boolean = false
) {
    val textValue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        value = textValue.value, // Access the value property of MutableState
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            unfocusedBorderColor = Secondary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = BGColor
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription ="")
        },
        isError = !errorStatus
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(labelValue: String, icon: ImageVector,
                      onTextSelected: (String) -> Unit,
                      errorStatus:Boolean = false) {

    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        value = password.value, // Access the value property of MutableState
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            unfocusedBorderColor = Secondary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = BGColor
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine = true,
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null)
        },
        trailingIcon = {
            var iconImage = if(passwordVisible.value){
                Icons.Filled.Visibility
            }else(Icons.Filled.VisibilityOff)
            var description = if(passwordVisible.value){
                stringResource(id = R.string.hide_password)
            }else{
                stringResource(id = R.string.show_password)
            }
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus

    )
}

@Composable
fun CheckboxComponent(value: String,onTextSelected:(String)->Unit, onCheckedChange : (Boolean) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically
        )
    {
        val checkedState = remember {
            mutableStateOf(false)
        }
        Checkbox(checked = checkedState.value, onCheckedChange = {
            checkedState.value = !checkedState.value
            onCheckedChange.invoke(it)
        })
        ClickableTextComponent(value = value, onTextSelected)
    }

}

@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit){
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsAndConditionText = "Terms of Use"
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorWhite))){
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorWhite))){
            pushStringAnnotation(tag = termsAndConditionText, annotation = termsAndConditionText)
            append(termsAndConditionText)
        }
    }
    ClickableText(text = annotatedString,onClick = { offset ->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also{span->
                Log.d("ClickableTextComponent","{$span}")

                if((span.item == termsAndConditionText) || (span.item == privacyPolicyText)){
                    onTextSelected(span.item)
                }
            }
    })

}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ButtonComponent(value: String, onButtonClicked : () -> Unit, isEnabled: Boolean = true){
    androidx.compose.material3.
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick ={
                onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled,
        border = BorderStroke(2.dp, ColorTheme)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(ButtonColor1, ButtonColor2)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ColorTheme,
                fontFamily = Font.toFontFamily())
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SmallButtonComponent(width: Int = 64,value: String, onButtonClicked : () -> Unit, isEnabled: Boolean = true){
    androidx.compose.material3.
    Button(
        modifier = Modifier
            .widthIn(width.dp)
            .heightIn(48.dp),
        onClick ={
                onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled,
        border = BorderStroke(2.dp, ColorTheme)
    ) {
        Box(modifier = Modifier
            .widthIn(width.dp)
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(ButtonColor1, ButtonColor2)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = ColorTheme,
                fontFamily = Font.toFontFamily())
        }
    }
}

@Composable
fun DividerTextComponent(){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = ColorTheme,
            thickness = 1.dp
        )
        Text(text = stringResource(id = R.string.or),
            modifier = Modifier.padding(8.dp),
            fontSize = 18.sp,
            color = ColorTheme,
            fontFamily = Font.toFontFamily())
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = ColorTheme,
            thickness = 1.dp
        )
    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit){
    val initialText = if(tryingToLogin) stringResource(id = R.string.go_to_login)
    else stringResource(id = R.string.go_to_sign_up)
    val loginText = if(tryingToLogin) stringResource(id = R.string.login)
    else stringResource(id = R.string.register)

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = ColorTheme)){
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
    ClickableText(modifier = Modifier
        .fillMaxWidth()
        .widthIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            color = ColorTheme
        ),
        text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also{span->
                Log.d("ClickableTextComponent","{$span}")

                if(span.item == loginText){
                    onTextSelected(span.item)
                }
            }
    })

}

@Composable
fun UnderLinedTextComponent(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        ),
        color = ColorTheme,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline,
        fontFamily = Font.toFontFamily()
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun IconComponent(value: String, onIconClicked : () -> Unit, isEnabled: Boolean = true, painterResource: Painter,
                  color: Color = ColorTheme, color1: Color = ButtonColor1, color2: Color = ButtonColor2,
                  fontResource: Font = Font){
    androidx.compose.material3.
    Button(modifier = Modifier
        .heightIn(125.dp)
        .widthIn(125.dp),
        onClick ={
                onIconClicked.invoke()
        },
        contentPadding =  PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(10.dp),
        enabled = isEnabled,
        border = BorderStroke(2.dp, color)
    ){
        Box(modifier = Modifier
            .heightIn(125.dp)
            .widthIn(125.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(color1, color2)),
                shape = RoundedCornerShape(10.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(painter = painterResource, contentDescription = "",modifier = Modifier.size(75.dp),tint = color)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = value,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = color,
                    fontFamily = fontResource.toFontFamily()
                )
        }
        }
    }
}

@Composable
fun PanicButtonComponent(onButtonClicked : () -> Unit, isEnabled: Boolean = true){
    androidx.compose.material3.
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(82.dp),
        onClick ={
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(82.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Color.Red, PanicColor1)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Text(text = stringResource(id = R.string.PANIC),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace)

        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SpinButtonComponent(result: String, onButtonClicked: () -> Unit, isEnabled: Boolean = true) {
    var showDialog by remember { mutableStateOf(false) }
    var soundeffect by remember { mutableStateOf(false) }
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(82.dp),
        onClick = {
            onButtonClicked.invoke()
            CoroutineScope(Dispatchers.Main).launch {
                delay(10500)
                showDialog = true
            }
            soundeffect = true
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled,
        border = BorderStroke(2.dp, ColorTheme)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(82.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(ButtonColor1, ButtonColor2)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.SPIN),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Font.toFontFamily(),
                color = ColorTheme
            )
        }
    }
    if(soundeffect){
        //MusicPlayer(audioResourceId = R.raw.spin)
        CoroutineScope(Dispatchers.Main).launch {
            delay(10500)
            soundeffect = false
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            containerColor = ButtonColor1,
            title = { Text(text = stringResource(id = R.string.winner), textAlign = TextAlign.Center,
                color = ColorTheme, fontWeight = FontWeight.Bold, fontFamily = Font.toFontFamily()) },
            text = {
                Text(
                    text = result,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .widthIn(40.dp)
                        .padding(end = 8.dp),
                    overflow = TextOverflow.Visible,
                    maxLines = Int.MAX_VALUE,
                    color = ColorTheme,
                    fontFamily = Font.toFontFamily()
                )
            },
            confirmButton = {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        showDialog = false
                    },
                    contentPadding = PaddingValues(),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(2.dp, ColorTheme)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(40.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        ButtonColor1,
                                        ButtonColor2
                                    )
                                ),
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(stringResource(id = R.string.ok),color = ColorTheme, fontFamily = Font.toFontFamily())
                    }
                }
            }
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CheerUpButtonComponent(dataViewModel: CheerupsDataViewModel = viewModel(), value: String, isEnabled: Boolean = true, painterResource: Painter,
                           color: Color = ColorTheme, color1: Color = ButtonColor1, color2: Color = ButtonColor2,
                           fontResource: Font = Font) {
    var notificationText by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val cheerupsHashMap: HashMap<String, String> = dataViewModel.state.value
    var randomString: String
    if(cheerupsHashMap.isNotEmpty()) {
        val randomKey = cheerupsHashMap.keys.random()
        randomString = cheerupsHashMap[randomKey].toString()
    }
    else{
        randomString = "placeholder"
    }
    Button(modifier = Modifier
        .heightIn(125.dp)
        .widthIn(125.dp),
        onClick ={
            notificationText = value
            showDialog = true
        },
        contentPadding =  PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(10.dp),
        enabled = isEnabled,
        border = BorderStroke(2.dp, color)
    ){
        Box(modifier = Modifier
            .heightIn(125.dp)
            .widthIn(125.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(color1, color2)),
                shape = RoundedCornerShape(10.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(painter = painterResource, contentDescription = "",modifier = Modifier.size(75.dp), tint = color)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = value,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = color,
                    fontFamily = fontResource.toFontFamily()
                )
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false},
            title = { Text(text = "Your thought of the day!", textAlign = TextAlign.Center,
                color = color,fontWeight = FontWeight.Bold, fontFamily = fontResource.toFontFamily()) },
            text = {
                Text(text = randomString,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .widthIn(40.dp)
                        .padding(end = 8.dp),
                    overflow = TextOverflow.Visible,
                    softWrap = true,
                    maxLines = Int.MAX_VALUE,
                    color = color,
                    fontFamily = fontResource.toFontFamily()
                    )
            },
            containerColor = color1,
            modifier = Modifier.background(
                color = color1,
                shape = RoundedCornerShape(10.dp)
            ),
            confirmButton = {
                Button(modifier = Modifier.fillMaxWidth(),onClick = {
                        showDialog = false
                },contentPadding =  PaddingValues(),
                    border = BorderStroke(2.dp, color),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    shape = RoundedCornerShape(10.dp),) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(40.dp)
                        .background(
                            brush = Brush.horizontalGradient(listOf(color1, color2)),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(id = R.string.thanks), color = color,fontFamily = fontResource.toFontFamily())
                }
                }
            }

        )
    }
}

@Composable
fun TaskComponent(item: String, onDeleteClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(ButtonColor1, Color.Transparent),
                    startX = 500f,
                    endX = 1000f
                ),
                shape = RoundedCornerShape(8.dp),
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (item.length > 15) {
                    "${item.take(15)}..."
                } else {
                item
            },
                fontSize = 25.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                overflow = TextOverflow.Visible,
                softWrap = true,
                color = ColorTheme,
                fontFamily = Font.toFontFamily()
            )
            SmallButtonComponent(
                value = stringResource(id = R.string.delete),
                onButtonClicked = onDeleteClicked
            )
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TaskText(tasks: MutableList<String>) {
    val localFocusManager = LocalFocusManager.current
    var textValue by remember { mutableStateOf("") }
    val items = remember { mutableStateListOf<String>().apply { addAll(tasks) }  }
    val db = Firebase.firestore
    val id = Firebase.auth.currentUser!!.uid
    var soundeffect by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = textValue,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = ColorTheme,
                unfocusedTextColor = ColorTheme,
                cursorColor = ColorTheme,
                focusedBorderColor = ColorTheme,
                unfocusedBorderColor = ColorTheme,
                disabledBorderColor = ButtonColor2,
            ),
            onValueChange = { textValue = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions {
                localFocusManager.clearFocus()
            },
            maxLines = 1,
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        ButtonComponent(value = stringResource(id = R.string.addtask),
            onButtonClicked = {
                if (textValue.isNotBlank()) {
                    items.add(textValue)
                    (tasks).remove(textValue)
                    val tasksList = items.map { it.toString() }
                    db.collection("users").document(id).update("tasks", tasksList)
                    textValue = ""
                }
            })
        Spacer(modifier = Modifier.height(10.dp))
        ButtonComponent(value = stringResource(id = R.string.spin),
            onButtonClicked = { HeartStitcherRouter.navigateTo(Screen.WheelScreen)})

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumnItems(
            items = items,
            onDeleteClicked = { item ->
                soundeffect = true
                items.remove(item)
                (tasks).remove(item)
                val tasksList = items.map { it.toString() }
                db.collection("users").document(id).update("tasks", tasksList)
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        if(soundeffect){
            MusicPlayer(audioResourceId = R.raw.delete)
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                soundeffect = false
            }
        }
    }
}

@Composable
fun LazyColumnItems(items: List<String>, onDeleteClicked: (String) -> Unit) {
    LazyColumn {
        items(items) { item ->
            TaskComponent(item = item, onDeleteClicked = { onDeleteClicked(item) })
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun MusicPlayer(loop: Boolean = false, audioResourceId: Int, isPlaying: Boolean = true) {
    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(context, audioResourceId).apply {
            isLooping = loop // Loop the audio track
        }
    }

    DisposableEffect(mediaPlayer) {
        onDispose {
            mediaPlayer.release()
        }
    }

    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start() // Start playback if isPlaying is true
            }
        } else {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause() // Pause playback if isPlaying is false
            }
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ThemeIcon(
    value: String,
    onIconClicked: () -> Unit,
    isEnabled: Boolean = true,
    painterResource: Int,
    textColor: Color = ColorTheme,
    fontResource: Font = Font
) {
    Box(
        modifier = Modifier
            .height(198.dp)
            .width(132.dp) // Adjust width as needed
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = { onIconClicked.invoke() },
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            shape = RoundedCornerShape(10.dp),
            enabled = isEnabled,
            border = BorderStroke(2.dp, textColor) // Border modifier added here
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = painterResource),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = value,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = textColor,
                        fontFamily = fontResource.toFontFamily()
                    )
                }
            }
        }
    }
}

@Composable
fun AchievementPreview(
    value: String,
    onIconClicked: () -> Unit,
    isEnabled: Boolean = true,
    bitmap: Bitmap? = null,
    onValueChange: (String) -> Unit,
    labelValue: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(0.dp)
            .border(
                width = 3.dp,
                color = ColorTheme,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                brush = Brush.horizontalGradient(listOf(ButtonColor1, ButtonColor2)),
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(280.dp) // Adjust size as needed,
                    .border(
                        width = 3.dp,
                        shape = RoundedCornerShape(10.dp),
                        brush = Brush.horizontalGradient(
                            colors = listOf(ColorTheme, ColorTheme),
                            startX = 500f,
                            endX = 1000f
                        )
                    )
                    .clickable(enabled = isEnabled) { onIconClicked.invoke() } // Make only this Box clickable
            ) {
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.add_image),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        tint = ColorTheme
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(17.dp))
                TextFieldComponent(
                    value = value,
                    onValueChange = onValueChange,
                    labelValue = labelValue
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}


@Composable
fun TextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    width: Int = 300
) {
    OutlinedTextField(
        value = value,
        label = { Text(text = labelValue, fontSize = 15.sp,
            color = ColorTheme,
            fontFamily = Font.toFontFamily())},
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = ColorTheme,
            unfocusedTextColor = ColorTheme,
            cursorColor = ColorTheme,
            focusedBorderColor = ColorTheme,
            unfocusedBorderColor = ColorTheme,
            disabledBorderColor = ButtonColor2,
        ),
        onValueChange = onValueChange,
        modifier = Modifier.width(width.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
        maxLines = 1,
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun AchievementComponent(
    value: String,
    isEnabled: Boolean = true,
    bitmap: Bitmap? = null
) {
    var showDialog by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(0.dp)
            .border(
                width = 3.dp,
                color = ColorTheme,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                brush = Brush.horizontalGradient(listOf(ButtonColor1, ButtonColor2)),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(enabled = isEnabled) { showDialog = true },// Make only this Box clickable,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(bitmap != null){
                Box(
                    modifier = Modifier
                        .size(110.dp) // Adjust size as needed,
                        .border(
                            width = 2.dp,
                            shape = RoundedCornerShape(10.dp),
                            brush = Brush.horizontalGradient(
                                colors = listOf(ColorTheme, ColorTheme),
                                startX = 500f,
                                endX = 1000f
                            )
                        )
                ) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = if (value.length > 15) {
                        "${value.take(15)}..."
                    } else {
                        value
                    },
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = ColorTheme,
                    fontFamily = Font.toFontFamily(),
                    modifier = Modifier.width(130.dp)
                )
            }
            else{
                Text(
                    text = if (value.length > 80) {
                        "${value.take(80)}..."
                    } else {
                        value
                    },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = ColorTheme,
                    fontFamily = Font.toFontFamily(),
                    modifier = Modifier.width(130.dp)
                )
            }
        }
    }
    if(showDialog && bitmap == null)
        AlertDialog(
            onDismissRequest = { showDialog = false},
            title = { Text(text = stringResource(id = R.string.memento), textAlign = TextAlign.Center,
                color = ColorTheme,fontWeight = FontWeight.Bold, fontFamily = Font.toFontFamily() ) },
            text = {
                Text(
                    text = value,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .widthIn(40.dp)
                        .padding(end = 8.dp),
                    overflow = TextOverflow.Visible,
                    softWrap = true,
                    maxLines = Int.MAX_VALUE,
                    color = ColorTheme,
                    fontFamily = Font.toFontFamily()
                )
            },
            containerColor = ButtonColor1,
            modifier = Modifier.background(
                color = ButtonColor1,
                shape = RoundedCornerShape(10.dp)
            ),
            confirmButton = {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { showDialog = false },
                    contentPadding = PaddingValues(),
                    border = BorderStroke(2.dp, ColorTheme),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(40.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        ButtonColor1,
                                        ButtonColor2
                                    )
                                ),
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(stringResource(id = R.string.okay), color = ColorTheme,fontFamily = Font.toFontFamily())
                    }
                }
            }
        )
    else if (showDialog && bitmap != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = stringResource(id = R.string.memento), textAlign = TextAlign.Center, color = ColorTheme, fontWeight = FontWeight.Bold, fontFamily = Font.toFontFamily()) },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {Box(
                    modifier = Modifier
                        .size(200.dp)
                        .border(
                            width = 3.dp,
                            color = ColorTheme, // Border color
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(220.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )}
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        text = value,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        overflow = TextOverflow.Visible,
                        softWrap = true,
                        maxLines = Int.MAX_VALUE,
                        color = ColorTheme,
                        fontFamily = Font.toFontFamily()
                    )
                }
            },
            containerColor = ButtonColor1,
            modifier = Modifier.background(
                color = ButtonColor1,
                shape = RoundedCornerShape(10.dp)
            ),
            confirmButton = {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { showDialog = false },
                    contentPadding = PaddingValues(),
                    border = BorderStroke(2.dp, ColorTheme),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(40.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        ButtonColor1,
                                        ButtonColor2
                                    )
                                ),
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(stringResource(id = R.string.okay), color = ColorTheme,fontFamily = Font.toFontFamily())
                    }
                }
            }
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SettingsIconComponent(
    value: String,
    onIconClicked: () -> Unit,
    isEnabled: Boolean = true,
    painterResource: Painter,
    alternatePainterResource: Painter,
    useAlternate: Boolean = true,
    color: Color = ColorTheme,
    color1: Color = ButtonColor1,
    color2: Color = ButtonColor2,
    fontResource: Font = Font
) {
    val currentPainter = if (!useAlternate) alternatePainterResource else painterResource

    androidx.compose.material3.Button(
        modifier = Modifier
            .heightIn(125.dp)
            .widthIn(125.dp),
        onClick = {
            onIconClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(10.dp),
        enabled = isEnabled,
        border = BorderStroke(2.dp, color)
    ) {
        Box(
            modifier = Modifier
                .heightIn(125.dp)
                .widthIn(125.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(color1, color2)),
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = currentPainter,
                    contentDescription = "",
                    modifier = Modifier.size(75.dp),
                    tint = color
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = value,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = color,
                    fontFamily = fontResource.toFontFamily()
                )
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun AchievementsList(achievements: List<Pair<String, String>>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        items(achievements.chunked(2)) { chunk ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                chunk.forEach { item ->
                    val bitmap: Bitmap? = if (item.second.isNotEmpty()) loadImageBitmap(item.second) else null
                    AchievementComponent(
                        value = item.first,
                        isEnabled = true,
                        bitmap = bitmap
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}


@Composable
fun loadImageBitmap(url: String): Bitmap {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap>(value = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)) }

    LaunchedEffect(url) {
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url)
            .build()

        val result = imageLoader.execute(request)
        if (result is SuccessResult) {
            bitmap = (result.drawable as BitmapDrawable).bitmap
        }
    }
    return bitmap
}


