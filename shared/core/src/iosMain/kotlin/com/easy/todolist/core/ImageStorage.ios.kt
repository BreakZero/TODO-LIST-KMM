package com.easy.todolist.core

actual class ImageStorage {
    actual suspend fun saveImage(bytes: ByteArray): String {
        return ""
    }
    actual suspend fun getImage(fileName: String): ByteArray? {
        return null
    }
    actual suspend fun deleteImage(fileName: String) {

    }
}