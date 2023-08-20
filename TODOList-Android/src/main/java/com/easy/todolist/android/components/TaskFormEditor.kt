package com.easy.todolist.android.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easy.todolist.android.R
import com.easy.todolist.android.common.DateTimeX
import com.easy.todolist.android.preview_data.defaultPreviewTask
import com.easy.todolist.model.Task

@Composable
fun TaskFormEditor(
    modifier: Modifier = Modifier,
    confirmButtonText: String,
    task: Task? = null,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    openDatePicker: () -> Unit,
    openImagePicker: () -> Unit,
    onConfirmed: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = task?.title.orEmpty(),
            onValueChange = onTitleChanged,
            placeholder = {
                Text(text = stringResource(id = R.string.edit_title))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 88.dp),
            value = task?.description.orEmpty(),
            onValueChange = onDescriptionChanged,
            placeholder = {
                Text(text = stringResource(id = R.string.edit_description))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = DateTimeX.formattedDate(task?.deadline),
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(text = stringResource(id = R.string.edit_deadline))
            },
            trailingIcon = {
                IconButton(onClick = openDatePicker) {
                    Icon(
                        imageVector = Icons.Default.EditCalendar, contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        )

        task?.let {
            Spacer(modifier = Modifier.height(8.dp))
            AttachmentPhoto(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .clickable {
                        openImagePicker()
                    },
                task = it
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onConfirmed
        ) {
            Text(text = confirmButtonText)
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun TaskFormEditor_Preview() {
    TaskFormEditor(
        modifier = Modifier.fillMaxWidth(),
        task = defaultPreviewTask,
        confirmButtonText = "Confirm",
        onTitleChanged = {},
        onDescriptionChanged = {},
        openDatePicker = { /*TODO*/ },
        openImagePicker = { /*TODO*/ }) {
    }
}