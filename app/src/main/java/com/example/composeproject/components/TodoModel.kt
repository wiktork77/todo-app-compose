package com.example.composeproject.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeproject.R
import com.example.composeproject.data.DBTodo
import com.example.composeproject.data.parsers.CategoryToDrawableParser
import com.example.composeproject.data.parsers.ImportanceToDrawableParser


@Composable
fun TodoModelGraphic(
    modifier: Modifier = Modifier,
    resource: Int,
    size: Dp = 32.dp,
) {
    Image(
        painter = painterResource(id = resource),
        contentDescription = null,
        modifier = modifier.size(size),
    )
}



@Composable
fun TodoModelText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle
) {
    Text(
        text = text,
        style = style,
        modifier = modifier,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}


@Composable
fun TodoModelInfo(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 28.dp, end = 10.dp),
    ) {
        TodoModelText(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 7.dp)
        )
        TodoModelText(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TodoModel(
    modifier: Modifier = Modifier,
    dbModel: DBTodo = DBTodo.getSampleTodo(),
    onclickBehavior: () -> Unit = {},
    onLongClickBehavior: () -> Unit = {}
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp)
            .combinedClickable(
                onClick = {onclickBehavior()},
                onLongClick = {onLongClickBehavior()}
            ),
    ) {
        Row(
            modifier = Modifier
                .padding(start = 20.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TodoModelGraphic(
                resource = CategoryToDrawableParser.parse(dbModel.category)
            )
            TodoModelInfo(
                title = dbModel.title,
                subtitle = dbModel.subTitle,
                modifier = Modifier.weight(1f)
            )
            TodoModelGraphic(
                resource = ImportanceToDrawableParser.parse(dbModel.importance),
                size = 26.dp,
                modifier = Modifier.padding(end = 20.dp)
            )
        }
    }
}


@Preview(showBackground = false)
@Composable
fun TodoModelPreview() {
    TodoModel(

    )
}