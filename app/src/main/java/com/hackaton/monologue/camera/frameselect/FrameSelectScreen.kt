package com.hackaton.monologue.camera.frameselect

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackaton.monologue.addFocusCleaner
import com.hackaton.monologue.ui.theme.Black
import com.hackaton.monologue.ui.theme.Blue
import com.hackaton.monologue.ui.theme.White
import com.hackaton.monologue.ui.theme.pretendard

@Composable
fun FrameSelectScreen(
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    var isAll by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .addFocusCleaner(focusManager)
    ) {
        FrameSelectTop(
            onNextClick = onNextClick
        )
        FrameSelectFilter(
            onAllClick = {
                if (!isAll) {
                    isAll = true
                }
            },
            onLikeClick = {
                if (isAll) {
                    isAll = false
                }
            }
        )
    }
}

@Composable
fun FrameSelectTop(
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(
                start = 30.dp,
                end = 30.dp,
                top = 20.dp
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "프레임 선택",
            style = TextStyle(
                color = Black,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )
        )
        Text(
            text = "다음",
            style = TextStyle(
                color = Blue,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            ),
            modifier = modifier.clickable {
                onNextClick()
            }
        )
    }
}

@Composable
fun FrameSelectFilter(
    modifier: Modifier = Modifier,
    onAllClick: () -> Unit,
    onLikeClick: () -> Unit
) {
    var isAll by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .padding(
                start = 30.dp,
                top = 20.dp
            )
    ) {
        Button(
            onClick = onAllClick,
            shape = RoundedCornerShape(20.dp),
            modifier = modifier
                .wrapContentSize()
                .background(if (isAll) Black else White)
                .border(
                    width = 1.dp,
                    color = Black,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
            Text(
                text = "전체",
                style = TextStyle(
                    color = if (isAll) White else Black,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),
                modifier = modifier
                    .padding(
                        start = 4.dp,
                        end = 4.dp,
                        top = 0.dp,
                        bottom = 0.dp
                    )
            )
        }
        Text(
            text = "좋아요한 프레임",
            style = TextStyle(
                color = if (isAll) Black else White,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            modifier = modifier
                .padding(
                    start = 18.dp,
                    end = 18.dp,
                    top = 6.dp,
                    bottom = 6.dp
                )
                .clip(shape = RoundedCornerShape(20.dp))
                .background(if (isAll) White else Black)
                .border(
                    width = 1.dp,
                    color = Black,
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable {
                    onLikeClick()
                }
        )
    }
}