package com.easy.todolist.android.common

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

class ImagePicker(
    private val activity: Activity
) {
    private lateinit var getContent: ActivityResultLauncher<String>

    @Composable
    fun registerPicker(onImagePicked: (ByteArray) -> Unit) {
        this.getContent = rememberLauncherForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->
            uri?.let {
                activity.contentResolver.openInputStream(uri)?.use {
                    onImagePicked(it.readBytes())
                }
            }
        }
    }

    fun pickImage() {
        getContent.launch("image/*")
    }
}