package com.easy.todolist.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.easy.todolist.android.common.rememberBitmapFromBytes
import com.easy.todolist.model.Task

@Composable
fun AttachmentPhoto(
    modifier: Modifier = Modifier,
    task: Task
) {
    val bitmap = rememberBitmapFromBytes(bytes = task.attachment)
    val photoModifier = modifier.clip(RoundedCornerShape(24))
    if (bitmap == null) {
        Column(
            modifier = photoModifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.secondary,
                imageVector = Icons.Default.AddAPhoto,
                contentDescription = ""
            )
            Text(
                text = "Add Attachment(Optional, limit 2M)",
                style = MaterialTheme.typography.bodySmall
            )
        }
    } else {
        Image(
            modifier = photoModifier,
            contentScale = ContentScale.Fit,
            bitmap = bitmap,
            contentDescription = ""
        )
    }
}