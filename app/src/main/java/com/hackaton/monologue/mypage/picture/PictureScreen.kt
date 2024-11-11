package com.hackaton.monologue.mypage.picture

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hackaton.monologue.ui.MonoTop
import com.hackaton.monologue.ui.theme.Black
import com.hackaton.monologue.ui.theme.White
import com.hackaton.monologue.ui.theme.background
import com.hackaton.monologue.ui.theme.pretendard

@Composable
fun MyPagePictureScreen(modifier: Modifier = Modifier) {
    val pictureList: List<MyPagePictureData> = mutableListOf(
        MyPagePictureData("https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA5MjFfMjAz%2FMDAxNzI2ODU1NzM1MDY4.HfrOPftzL2oGtRpDxbg4T8kSZasHaQjsJjCtBlW_zHgg.dMCiTvVexnASIz9f2NCFogIPim-GFbW1xCNRspmyTTsg.PNG%2Fimage.png&type=a340", "3", "2"),
        MyPagePictureData("https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA5MjFfMjAz%2FMDAxNzI2ODU1NzM1MDY4.HfrOPftzL2oGtRpDxbg4T8kSZasHaQjsJjCtBlW_zHgg.dMCiTvVexnASIz9f2NCFogIPim-GFbW1xCNRspmyTTsg.PNG%2Fimage.png&type=a340", "4", "2"),
        MyPagePictureData("https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA5MjFfMjAz%2FMDAxNzI2ODU1NzM1MDY4.HfrOPftzL2oGtRpDxbg4T8kSZasHaQjsJjCtBlW_zHgg.dMCiTvVexnASIz9f2NCFogIPim-GFbW1xCNRspmyTTsg.PNG%2Fimage.png&type=a340", "3", "2"),
        MyPagePictureData("https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA5MjFfMjAz%2FMDAxNzI2ODU1NzM1MDY4.HfrOPftzL2oGtRpDxbg4T8kSZasHaQjsJjCtBlW_zHgg.dMCiTvVexnASIz9f2NCFogIPim-GFbW1xCNRspmyTTsg.PNG%2Fimage.png&type=a340", "3", "2"),
        MyPagePictureData("https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA5MjFfMjAz%2FMDAxNzI2ODU1NzM1MDY4.HfrOPftzL2oGtRpDxbg4T8kSZasHaQjsJjCtBlW_zHgg.dMCiTvVexnASIz9f2NCFogIPim-GFbW1xCNRspmyTTsg.PNG%2Fimage.png&type=a340", "3", "2")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(background)
    ) {
        MonoTop()
        LazyVerticalGrid(
            modifier = modifier.padding(
                top = 8.dp,
                start = 12.dp,
                end = 12.dp
            ),
            columns = GridCells.Fixed(3),
            state = rememberLazyGridState()
        ) {
            items(count = pictureList.size, span = { index ->
                GridItemSpan(1)
            }) { index ->
                val picture = pictureList[index]
                PictureItem(
                    picture = picture
                )
            }
        }
    }
}

@Composable
fun PictureItem(
    modifier: Modifier = Modifier,
    picture: MyPagePictureData
) {
    Box(
        modifier = modifier
            .padding(
                start = 1.dp,
                end = 1.dp,
                top = 1.dp,
                bottom = 1.dp
            )
    ) {
        AsyncImage(
            model = picture.imageUrl,
            contentDescription = null,
            modifier = modifier
                .align(Alignment.TopStart)
                .fillMaxSize()
        )
        Column(
            modifier = modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = 4.dp,
                    end = 4.dp
                )
                .wrapContentSize()
                .clip(shape = RoundedCornerShape(8.dp))
                .background(White)
                .padding(
                    start = 6.dp,
                    end = 6.dp,
                    top = 4.dp,
                    bottom = 4.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = picture.month + "월",
                style = TextStyle(
                    color = Black,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            )
            Text(
                text = picture.day + "일",
                style = TextStyle(
                    color = Black,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp
                )
            )
        }
    }
}