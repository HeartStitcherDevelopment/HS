@file:OptIn(ExperimentalMaterial3Api::class)

package com.wtfih.heartstitcher.components

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wtfih.heartstitcher.R
import com.wtfih.heartstitcher.data.CheerupsDataViewModel
import com.wtfih.heartstitcher.ui.theme.BGColor
import com.wtfih.heartstitcher.ui.theme.GrayColor
import com.wtfih.heartstitcher.ui.theme.PanicColor1
import com.wtfih.heartstitcher.ui.theme.Primary
import com.wtfih.heartstitcher.ui.theme.Secondary
import com.wtfih.heartstitcher.ui.theme.SpinButtonColor1
import com.wtfih.heartstitcher.ui.theme.TextColor
import com.wtfih.heartstitcher.ui.theme.componentShapes


@Composable
fun NormalTextComponent(value:String){
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
        ,color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value:String){
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
        ,color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
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
            Icon(painter = painterResource, contentDescription ="" )
        },
        isError = !errorStatus
    )
}

//@OptIn(ExperimentalMaterial3Api::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeTextField(onStringListChange: (List<String>) -> Unit,labelValue: String) {
    val stringList = remember { mutableStateListOf<String>() }
    val textFieldValue = remember { mutableStateOf(TextFieldValue()) }
    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(componentShapes.small),
            label = { Text(text = labelValue) },
            value = textFieldValue.value, // Access the value property of MutableState
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = Secondary,
                focusedLabelColor = Primary,
                cursorColor = Primary,
                containerColor = BGColor
            ),/*onImeActionPerformed = { action, _ ->
                if (action == ImeAction.Done && textFieldValue.value.text.isNotBlank()) {
                    stringList.add(textFieldValue.value.text)
                    onStringListChange(stringList)
                    textFieldValue.value = TextFieldValue()
                }
            },*/
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            singleLine = false,
            maxLines = 10,
            onValueChange = {
                textFieldValue.value = it
            },
            isError = false,
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(stringList) { string ->
                Text(text = string)
            }
        }
    }
}
@Composable
fun StringItem(text: String, onRemove: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(text = text, modifier = Modifier.weight(1f))
        IconButton(onClick = { onRemove(text) }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove string"
            )
        }
    }
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
    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also{span->
                Log.d("ClickableTextComponent","{$span}")

                if((span.item == termsAndConditionText) || (span.item == privacyPolicyText)){
                    onTextSelected(span.item)
                }
            }
    })

}

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
        enabled = isEnabled
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
        }
    }
}

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
        enabled = isEnabled
    ) {
        Box(modifier = Modifier
            .widthIn(width.dp)
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold)
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
            color = GrayColor,
            thickness = 1.dp
        )
        Text(text = stringResource(id = R.string.or),
            modifier = Modifier.padding(8.dp),
            fontSize = 18.sp,
            color = TextColor)
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = GrayColor,
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
        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorWhite))){
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
            textAlign = TextAlign.Center
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
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.colorWhite),
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}

@Composable
fun IconComponent(value: String, onIconClicked : () -> Unit, isEnabled: Boolean = true, painterResource: Painter){
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
    ){
        Box(modifier = Modifier
            .heightIn(125.dp)
            .widthIn(125.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                shape = RoundedCornerShape(10.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(painter = painterResource, contentDescription = "",modifier = Modifier.size(75.dp))
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = value,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
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


@Composable
fun SpinButtonComponent(onButtonClicked : () -> Unit, isEnabled: Boolean = true){
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
                brush = Brush.horizontalGradient(listOf(SpinButtonColor1, Color.Red)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Text(text = stringResource(id = R.string.SPIN),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace)

        }
    }
}



@Composable
fun CheerUpButtonComponent(dataViewModel: CheerupsDataViewModel = viewModel(), value: String, isEnabled: Boolean = true, painterResource: Painter) {
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
    ){
        Box(modifier = Modifier
            .heightIn(125.dp)
            .widthIn(125.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                shape = RoundedCornerShape(10.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(painter = painterResource, contentDescription = "",modifier = Modifier.size(75.dp))
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = value,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Your thought of the day!", textAlign = TextAlign.Center) },
            text = {
                Text(text = randomString,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .widthIn(40.dp)
                        .padding(end = 8.dp),
                    overflow = TextOverflow.Visible,
                    softWrap = true,
                    maxLines = Int.MAX_VALUE
                    )
            },
            confirmButton = {
                Button(modifier = Modifier.fillMaxWidth(),onClick = {
                    showDialog = false
                },contentPadding =  PaddingValues(),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    shape = RoundedCornerShape(10.dp),) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(40.dp)
                        .background(
                            brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Thanks!")
                }
                }
            }

        )
    }
}
















////TESTING ZONE
@Composable
fun TaskComponent(item: String, onDeleteClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp) // Adjust the height as needed
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color.Green, Color.Transparent),
                    startX = 100f, // Adjust the starting point of the gradient
                    endX = 650f // Adjust the ending point of the gradient
                ),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item,
                fontSize = 25.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                overflow = TextOverflow.Visible,
                softWrap = true
            )
            SmallButtonComponent(
                value = stringResource(id = R.string.delete),
                onButtonClicked = onDeleteClicked,
            )
        }
    }
}

@Composable
fun SpinText() {
    val localFocusManager = LocalFocusManager.current
    var textValue by remember { mutableStateOf("") }
    val items = remember { mutableStateListOf<String>() }
    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = textValue,
            onValueChange = { textValue = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions{
                localFocusManager.clearFocus()
            },
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (textValue.isNotBlank()) {
                    items.add(textValue)
                    textValue = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.addtask))
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumnItems(items = items, onDeleteClicked = { item ->
            items.remove(item)
        })
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





