package com.easy.todolist.android.common.decoder

import android.net.Uri

class UriDecoder : StringDecoder {
  override fun decodeString(encodedString: String): String = Uri.decode(encodedString)
}
