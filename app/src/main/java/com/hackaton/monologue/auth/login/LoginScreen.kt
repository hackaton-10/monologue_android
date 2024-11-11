package com.hackaton.monologue.auth.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackaton.monologue.addFocusCleaner
import com.hackaton.monologue.auth.AuthErrorMessage
import com.hackaton.monologue.auth.AuthInput
import com.hackaton.monologue.auth.AuthPassword
import com.hackaton.monologue.auth.AuthTitle
import com.hackaton.monologue.auth.LoginRequest
import com.hackaton.monologue.auth.login
import com.hackaton.monologue.ui.MonoButton
import com.hackaton.monologue.ui.theme.Black
import com.hackaton.monologue.ui.theme.Gray700
import com.hackaton.monologue.ui.theme.White
import com.hackaton.monologue.ui.theme.pretendard

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    onTextClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var buttonEnabled by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf(false) }

    if (id.isNotEmpty() && pw.isNotEmpty()) {
        buttonEnabled = true
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .addFocusCleaner(focusManager)
    ) {
        Column(
            modifier = modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTitle()
            Spacer(modifier = modifier.height(40.dp))
            AuthInput(
                input = id,
                hint = "아이디",
                onValueChange = { newId ->
                    id = newId
                }
            )
            AuthErrorMessage(message = "")
            AuthPassword(
                input = pw,
                hint = "비밀번호",
                onValueChange = { newPw ->
                    pw = newPw
                }
            )
            AuthErrorMessage(message = if (loginError) "아이디 또는 비밀번호를 확인해주세요" else "")
        }
        LoginButton(
            modifier = modifier.align(Alignment.BottomCenter),
            buttonEnabled = buttonEnabled,
            buttonText = "로그인",
            onButtonClick = {
                login(
                    loginRequest = LoginRequest(id, pw),
                    callback = { status ->
                        if (status == 200) {
                            onButtonClick()
                        } else {
                            loginError = true
                        }
                    }
                )
            },
            onTextClick = onTextClick
        )
    }
}

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    buttonEnabled: Boolean,
    buttonText: String,
    onButtonClick: () -> Unit,
    onTextClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(
                bottom = 46.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MonoButton(
            modifier = modifier.padding(
                bottom = 12.dp
            ),
            enabled = buttonEnabled,
            text = buttonText,
            onClick = onButtonClick
        )
        Row {
            Text(
                text = "아직 회원이 아니라면,",
                style = TextStyle(
                    color = Gray700,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            )
            Text(
                text = "회원가입",
                style = TextStyle(
                    color = Black,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                ),
                modifier = modifier.clickable {
                    onTextClick()
                }
            )
        }
    }
}