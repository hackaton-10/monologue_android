package com.hackaton.monologue.auth

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackaton.monologue.ui.theme.Black
import com.hackaton.monologue.ui.theme.Gray300
import com.hackaton.monologue.ui.theme.Gray400
import com.hackaton.monologue.ui.theme.Red
import com.hackaton.monologue.ui.theme.White
import com.hackaton.monologue.ui.theme.pretendard
import com.hackaton.monologue.ui.theme.pridi

@Composable
fun AuthTitle(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .padding(
                top = 100.dp
            ),
        text = "Monologue",
        style = TextStyle(
            color = Black,
            fontFamily = pridi,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            letterSpacing = 3.sp
        )
    )
}

@Composable
fun AuthPlaceholder(
    hint: String
) {
    Text(
        text = hint,
        style = TextStyle(
            color = Gray400,
            fontFamily = pretendard,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
        )
    )
}

@Composable
fun AuthInput(
    modifier: Modifier = Modifier,
    input: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = input,
        onValueChange = onValueChange,
        modifier = modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 8.dp
            )
            .fillMaxWidth(),
        placeholder = {
            AuthPlaceholder(hint = hint)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = White,
            focusedContainerColor = White,
            unfocusedBorderColor = Gray300,
            focusedBorderColor = Black,
            cursorColor = Black
        ),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun AuthPassword(
    modifier: Modifier = Modifier,
    input: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    var showPassword by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 8.dp
            )
            .fillMaxWidth(),
        value = input,
        onValueChange = onValueChange,
        placeholder = {
            AuthPlaceholder(hint = hint)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = {
                    showPassword = !showPassword
                }
            ) {
                val passwordIcon = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val contentDescription = if (showPassword) "Hide Password Icon" else "show Password Icon"
                Icon(
                    imageVector = passwordIcon,
                    contentDescription = contentDescription,
                    modifier = modifier.size(20.dp)
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = White,
            focusedContainerColor = White,
            unfocusedBorderColor = Gray300,
            focusedBorderColor = Black,
            cursorColor = Black
        ),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun AuthErrorMessage(
    modifier: Modifier = Modifier,
    message: String
) {
    Text(
        modifier = modifier
            .padding(
                start = 24.dp,
                end = 24.dp
            )
            .fillMaxWidth()
            .padding(
                start = 8.dp,
                top = 2.dp
            ),
        text = message,
        style = TextStyle(
            color = Red,
            fontFamily = pretendard,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp
        )
    )
}