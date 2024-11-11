package com.hackaton.monologue.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackaton.monologue.ui.theme.Black
import com.hackaton.monologue.ui.theme.Gray300
import com.hackaton.monologue.ui.theme.White
import com.hackaton.monologue.ui.theme.pretendard
import com.hackaton.monologue.ui.theme.pridi

@Composable
fun MonoButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .padding(
                start = 24.dp,
                end = 24.dp
            )
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = Gray300,
            disabledContentColor = White,
            containerColor = Black,
            contentColor = White
        )
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = White,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        )
    }
}

@Composable
fun MonoTop(modifier: Modifier = Modifier) {
    Text(
        text = "Monologue",
        style = TextStyle(
            color = Black,
            fontFamily = pridi,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            letterSpacing = 3.sp
        ),
        modifier = modifier
            .fillMaxWidth()
            .background(White)
            .padding(
                top = 10.dp,
                bottom = 10.dp
            ),
        textAlign = TextAlign.Center
    )
}