package com.easy.todolist.android.feature.todo_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.easy.todolist.android.common.DateTimeX
import com.easy.todolist.android.preview_data.defaultPreviewTask
import com.easy.todolist.model.Task

@Composable
fun TaskCardView(
    modifier: Modifier = Modifier,
    task: Task,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    onItemClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable {
            onItemClick()
        },
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = Color(task.accentColor))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                if (task.inProgress()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clock),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentDescription = null
                    )
                }
            }
            Text(
                text = task.description,
                maxLines = 3,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = DateTimeX.formattedDate(task.createAt),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview
@Composable
fun TaskCardPreview() {
    TaskCardView(
        task = defaultPreviewTask,
        onItemClick = {}
    )
}