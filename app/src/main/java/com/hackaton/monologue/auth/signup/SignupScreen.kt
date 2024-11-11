package com.hackaton.monologue.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.hackaton.monologue.addFocusCleaner
import com.hackaton.monologue.auth.AuthErrorMessage
import com.hackaton.monologue.auth.AuthInput
import com.hackaton.monologue.auth.AuthPassword
import com.hackaton.monologue.auth.AuthTitle
import com.hackaton.monologue.auth.SignUpRequest
import com.hackaton.monologue.auth.signUp
import com.hackaton.monologue.ui.MonoButton
import com.hackaton.monologue.ui.theme.White

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var pwCheck by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    
    var idError by remember { mutableStateOf(false) }
    var pwCheckError by remember { mutableStateOf(false) }
    var buttonEnabled by remember { mutableStateOf(false) }

    if (id.length in 5..20 && pw.length in 8..20 && !pwCheckError && nickname.isNotEmpty()) {
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
                hint = "아이디 (5 ~ 20자)",
                onValueChange = { newId ->
                    id = newId
                    if (idError) {
                        idError = false
                    }
                }
            )
            AuthErrorMessage(message = if (idError) "사용할 수 없는 아이디입니다" else "")
            AuthPassword(
                input = pw, 
                hint = "비밀번호 (8 ~ 20자)",
                onValueChange = { newPw ->
                    pw = newPw
                    pwCheckError = pwCheck != pw
                }
            )
            AuthErrorMessage(message = "")
            AuthPassword(
                input = pwCheck, 
                hint = "비밀번호 확인",
                onValueChange = { newPwCheck ->
                    pwCheck = newPwCheck
                    pwCheckError = pwCheck != pw
                }
            )
            AuthErrorMessage(message = if (pwCheckError) "비밀번호가 일치하지 않습니다" else "")
            AuthInput(
                input = nickname,
                hint = "사용하실 닉네임을 입력해주세요",
                onValueChange = { newNickname ->
                    nickname = newNickname
                }
            )
        }
        MonoButton(
            enabled = buttonEnabled,
            text = "회원가입",
            onClick = {
                signUp(
                    signUpRequest = SignUpRequest(
                        id = id,
                        password = pw,
                        name = nickname
                    ),
                    callback = { status ->
                        if(status == 201) {
                            onButtonClick()
                        } else {
                            idError = true
                        }
                    }
                )
            },
            modifier = modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        )
    }
}