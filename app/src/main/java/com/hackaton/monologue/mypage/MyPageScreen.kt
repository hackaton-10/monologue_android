package com.hackaton.monologue.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hackaton.monologue.R
import com.hackaton.monologue.ui.ConfirmDialog
import com.hackaton.monologue.ui.MonoTop
import com.hackaton.monologue.ui.theme.Black
import com.hackaton.monologue.ui.theme.Gray200
import com.hackaton.monologue.ui.theme.Gray300
import com.hackaton.monologue.ui.theme.Red
import com.hackaton.monologue.ui.theme.White
import com.hackaton.monologue.ui.theme.background
import com.hackaton.monologue.ui.theme.pretendard

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    onClickLikeFrame: () -> Unit,
    onClickMyFrame: () -> Unit,
    onClickPicture: () -> Unit,
    onClickProfile: () -> Unit,
    onClickLogout: () -> Unit,
    onClickMemberOut: () -> Unit
) {
    var isShowLogoutDialog by remember { mutableStateOf(false) }
    var isShowMemberOutDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(background)
    ) {
        MonoTop()
        MyPageProfile()
        Spacer(modifier = modifier.height(24.dp))
        SingleOption(
            text = "좋아요한 프레임",
            onClick = onClickLikeFrame
        )
        DoubleOption(
            text1 = "내가 만든 프레임",
            text2 = "촬영한 사진",
            onText1Click = onClickMyFrame,
            onText2Click = onClickPicture
        )
        DoubleOption(
            text1 = "프로필 수정",
            text2 = "로그아웃",
            onText1Click = onClickProfile,
            onText2Click = {
                isShowLogoutDialog = true
            }
        )
        SingleOption(
            text = "회원 탈퇴",
            onClick = {
                isShowMemberOutDialog = true
            },
            isMemberOut = true
        )
    }
    if (isShowLogoutDialog) {
        ConfirmDialog(
            title = "로그아웃하시겠습니까?",
            onClickConfirm = {
                isShowLogoutDialog = false
                onClickLogout()
            },
            onClickCancel = {
                isShowLogoutDialog = false
            }
        )
    }
    if (isShowMemberOutDialog) {
        ConfirmDialog(
            title = "탈퇴하시겠습니까?",
            onClickConfirm = {
                isShowLogoutDialog = false
                onClickMemberOut()
            },
            onClickCancel = {
                isShowMemberOutDialog = false
            },
            isContent = true,
            content = "탈퇴 후 되돌릴 수 없습니다"
        )
    }
}

@Composable
fun MyPageProfile(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 30.dp
            )
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(White)
            .border(
                width = 1.dp,
                color = Gray200,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(
                start = 30.dp,
                end = 30.dp,
                top = 20.dp,
                bottom = 20.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = null,
            contentDescription = "profile",
            placeholder = painterResource(id = R.drawable.default_profile),
            modifier = modifier
                .size(80.dp)
                .clip(shape = RoundedCornerShape(40.dp))
                .border(
                    width = 1.dp,
                    color = Gray300,
                    shape = RoundedCornerShape(40.dp)
                )
        )
        Column(
            modifier = modifier
                .padding(
                    start = 20.dp
                )
        ) {
            Text(
                text = "익명이",
                style = TextStyle(
                    color = Black,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp
                ),
                modifier = modifier
                    .padding(
                        bottom = 4.dp
                    )
            )
            Text(
                text = "dkdlel",
                style = TextStyle(
                    color = Black,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
fun OptionText(
    modifier: Modifier = Modifier,
    text: String,
    isMemberOut: Boolean = false
) {
    Text(
        text = text,
        style = TextStyle(
            color = if (isMemberOut) Red else Black,
            fontFamily = pretendard,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        ),
        modifier = modifier
            .padding(
                start = 24.dp,
                top = 14.dp,
                bottom = 14.dp
            )
    )
}

@Composable
fun SingleOption(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isMemberOut: Boolean = false
) {
    OptionText(
        text = text,
        modifier = modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 16.dp
            )
            .fillMaxWidth()
            .background(White)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = Gray200,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onClick()
            },
        isMemberOut = isMemberOut
    )
}

@Composable
fun DoubleOption(
    modifier: Modifier = Modifier,
    text1: String,
    text2: String,
    onText1Click: () -> Unit,
    onText2Click: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 16.dp
            )
            .fillMaxWidth()
            .background(White)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = Gray200,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        OptionText(
            text = text1,
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    onText1Click()
                }
        )
        HorizontalDivider(
            modifier = modifier
                .fillMaxWidth()
                .padding(0.dp),
            color = Gray200
        )
        OptionText(
            text = text2,
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    onText2Click()
                }
        )
    }
}