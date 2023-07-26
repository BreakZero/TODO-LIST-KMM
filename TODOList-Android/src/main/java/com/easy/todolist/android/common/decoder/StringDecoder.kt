package com.easy.todolist.android.common.decoder

interface StringDecoder {
  fun decodeString(encodedString: String): String
}
