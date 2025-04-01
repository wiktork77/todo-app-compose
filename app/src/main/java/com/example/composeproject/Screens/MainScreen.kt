package com.example.composeproject.Screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeproject.R

@Composable
fun MainScreenText(
    modifier: Modifier = Modifier,
    title: String,
    style: TextStyle
) {
    Text(
        text = title,
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 32.dp),
        style = style,
        textAlign = TextAlign.Center
    )
}

@Composable
fun MainScreenAvatar(
    modifier: Modifier = Modifier,
    avatarId: Int,
    contentScale: ContentScale
) {
    Image(
        painter = painterResource(id = avatarId),
        contentDescription = null,
        modifier = modifier.fillMaxWidth(),
        contentScale = contentScale
    )
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    avatarId: Int = R.drawable.image_sun,
) {
    val context = LocalContext.current
    val sp = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState())
            .padding(bottom = 35.dp)
    ) {
        MainScreenText(
            title = "${sp.getString("name", "")} ${sp.getString("age", "")}",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.paddingFromBaseline(bottom = 32.dp).padding(top=20.dp)
        )
        MainScreenAvatar(
            avatarId = sp.getInt("avatarId", R.drawable.ic_other_image),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    PaddingValues(horizontal = 32.dp)
                ).clip(RoundedCornerShape(32.dp)),
            contentScale = ContentScale.FillWidth
        )
        MainScreenText(
            title = "${sp.getString("info", "")}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.paddingFromBaseline(top = 32.dp)
        )
    }
}




@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}