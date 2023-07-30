package com.easy.todolist.android.feature.todo_list.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easy.todolist.android.R
import com.easy.todolist.android.enum.TaskCategory

@Composable
fun TaskFilterHeader(
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.primary,
    onResult: (TaskCategory) -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = null,
            tint = tint
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "LIST OF TODO",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_filter),
            tint = tint,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilterHeaderPreview() {
    TaskFilterHeader()
}