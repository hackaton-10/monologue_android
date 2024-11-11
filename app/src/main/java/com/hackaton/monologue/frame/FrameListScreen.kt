package com.hackaton.monologue.frame

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hackaton.monologue.R
import com.hackaton.monologue.addFocusCleaner
import com.hackaton.monologue.auth.AuthPlaceholder
import com.hackaton.monologue.ui.MonoTop
import com.hackaton.monologue.ui.theme.Black
import com.hackaton.monologue.ui.theme.Gray400
import com.hackaton.monologue.ui.theme.background

@Composable
fun FrameListScreen(
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    val frameList = mutableListOf<FrameInfo>(
        FrameInfo("프레임1", listOf("이번", "기회", "컴포즈", "연습"), "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAxMjZfMjQg%2FMDAxNzA2MjgwMTA5ODcw.ccIIxogDqH9pBd8euK04kpvn_7ijOy99-R_f8AtgPjkg.6cMrjLM8AJK3aikVwstRw6h9mFO3a6S_YbF-uuSbokIg.PNG.mix_you%2F%25BE%25C6%25C0%25CC%25C0%25AF_love_wins_all_%25B9%25C2%25C1%25F7%25BA%25F1%25B5%25F0%25BF%25C0.png&type=a340", "익명이", false),
        FrameInfo("프레임1", listOf("이번", "기회", "컴포즈", "연습"), "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAxMjZfMjQg%2FMDAxNzA2MjgwMTA5ODcw.ccIIxogDqH9pBd8euK04kpvn_7ijOy99-R_f8AtgPjkg.6cMrjLM8AJK3aikVwstRw6h9mFO3a6S_YbF-uuSbokIg.PNG.mix_you%2F%25BE%25C6%25C0%25CC%25C0%25AF_love_wins_all_%25B9%25C2%25C1%25F7%25BA%25F1%25B5%25F0%25BF%25C0.png&type=a340", "익명이", true),
        FrameInfo("프레임1", listOf("이번", "기회", "컴포즈", "연습"), "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAxMjZfMjQg%2FMDAxNzA2MjgwMTA5ODcw.ccIIxogDqH9pBd8euK04kpvn_7ijOy99-R_f8AtgPjkg.6cMrjLM8AJK3aikVwstRw6h9mFO3a6S_YbF-uuSbokIg.PNG.mix_you%2F%25BE%25C6%25C0%25CC%25C0%25AF_love_wins_all_%25B9%25C2%25C1%25F7%25BA%25F1%25B5%25F0%25BF%25C0.png&type=a340", "익명이", false)
    )

    var search by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(background)
            .addFocusCleaner(focusManager)
    ) {
        MonoTop()
        SearchFrame(
            input = search,
            onValueChange = { newSearch ->
                search = newSearch
            }
        )
        LazyVerticalGrid(
            modifier = modifier.padding(
                top = 8.dp,
                start = 12.dp,
                end = 12.dp
            ),
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState()
        ) {
            items(count = frameList.size, span = { index ->
                GridItemSpan(1)
            }) { index ->
                val frame = frameList[index]
                FrameListItem(
                    frame = frame
                )
            }
        }
    }
}

@Composable
fun SearchFrame(
    modifier: Modifier = Modifier,
    input: String,
    onValueChange: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = input,
        onValueChange = onValueChange,
        modifier = modifier
            .padding(
                start = 30.dp,
                end = 30.dp,
                top = 40.dp
            )
            .fillMaxWidth(),
        placeholder = {
            AuthPlaceholder(hint = "검색할 프레임을 입력해주세요")
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onDone = {
            // 전송
            keyboardController?.hide()
        }),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                modifier = modifier
                    .size(24.dp)
                    .clickable {
                        // 전송
                        keyboardController?.hide()
                    }
            )
        }
    )
}

@Composable
fun FrameListItem(
    modifier: Modifier = Modifier,
    frame: FrameInfo
) {
    Column(
        modifier = modifier
            .padding(
                start = 20.dp,
                end = 10.dp,
                top = 6.dp,
                bottom = 6.dp
            )
    ) {
        Box (
            modifier = modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = frame.imageUrl,
                contentDescription = null,
                modifier = modifier
                    .align(Alignment.TopStart)
                    .fillMaxSize()
            )
            Icon(
                painter = painterResource(id = if (frame.isLiked) R.drawable.heart_after else R.drawable.heart_before),
                contentDescription = "heart",
                modifier = modifier
                    .size(20.dp)
                    .clickable {
                        if (frame.isLiked) {
                            frame.isLiked = false
                        } else {
                            frame.isLiked = true
                        }
                    }
            )
        }
    }
}