package com.example.composeproject.Screens

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeproject.data.AvatarsRepository
import com.example.composeproject.components.UserInput
import com.example.composeproject.stateholders.AppScreenStateHolder
import com.example.composeproject.stateholders.SettingsScreenStateHolder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AvatarsPager(
    modifier: Modifier = Modifier,
    size: Dp = 240.dp,
    avatarId: Int,
    onSlide: (Int) -> Unit = {}
) {
    val avatars: List<Int> = AvatarsRepository.getAvatars()
    val pagerState = rememberPagerState(pageCount = {avatars.size})
    val currentIndex = avatars.indexOf(avatarId)
    LaunchedEffect(key1 = avatars) {
        pagerState.scrollToPage(currentIndex)
    }
    HorizontalPager(
        modifier = modifier.size(size),
        state = pagerState,
    ) { page ->
        Image(
            painter = painterResource(id = avatars[page]),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        onSlide(avatars[pagerState.currentPage])
    }
}

@Composable
fun SettingText(
    modifier: Modifier = Modifier,
    style: TextStyle,
    title: String,
) {
    Text(
        text = title,
        modifier = modifier,
        style = style
    )
}


@Composable
fun EditDataSection(
    modifier: Modifier = Modifier,
    name: String,
    age: String,
    info: String,
    onInputChange: (String, String) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SettingText(
            style = MaterialTheme.typography.bodyLarge,
            title = "Modify your data",
            modifier = Modifier.padding(vertical = 12.dp)
        )
        UserInput(
            style = MaterialTheme.typography.bodyMedium,
            title = "Name",
            tfValue = name,
            onValueChange = {str ->
                onInputChange(str, "name")
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        UserInput(
            style = MaterialTheme.typography.bodyMedium,
            title = "Age",
            tfValue = age,
            onValueChange = {str ->
                onInputChange(str, "age")
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        UserInput(
            style = MaterialTheme.typography.bodyMedium,
            title = "Info",
            tfValue = info,
            onValueChange = {str ->
                onInputChange(str, "info")
            }
        )
    }
}


@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    avatarId: Int,
    name: String,
    age: String,
    info: String,
    onSlide: (Int) -> Unit,
    onInputChange: (String, String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SettingText(
            style = MaterialTheme.typography.bodyLarge,
            title = "Select avatar",
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
        )
        AvatarsPager(
            avatarId = avatarId,
            onSlide = {avatarId -> onSlide(avatarId)}
        )
        EditDataSection(
            name = name,
            age = age,
            info = info,
            onInputChange = {str1, str2 -> onInputChange(str1, str2)}
        )
    }
}


//@Preview
//@Composable
//fun SettingsScreenPreview() {
//    SettingsScreen()
//}