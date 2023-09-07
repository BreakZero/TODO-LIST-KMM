package com.easy.todolist.core.common

import com.easy.todolist.core.commom.DateTimeDecoder
import com.easy.todolist.core.commom.systemCurrentMilliseconds
import com.easy.todolist.core.platform.DateFormatHelper
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import kotlinx.datetime.toInstant
import kotlinx.datetime.toNSDate
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import platform.Foundation.NSData
import platform.Foundation.NSDate
import platform.Foundation.create
import platform.UIKit.UIImage
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName(swiftName = "CommonHelper")
object CommonHelper : KoinComponent {
    private val dateFormatHelper: DateFormatHelper by inject()

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    fun getUIImageFromBytes(bytes: ByteArray): UIImage {
        val data = bytes.usePinned {
            NSData.create(
                bytes = it.addressOf(0),
                length = bytes.size.toULong()
            )
        }
        return UIImage(data)
    }

    fun timestampToDate(timestamp: Long): NSDate {
        val localDateTime = DateTimeDecoder.decodeToDateTime(timestamp)
        return localDateTime.toInstant(DateTimeDecoder.defaultTimeZone).toNSDate()
    }

    fun dateFormatted(timestamp: Long): String {
        return dateFormatHelper.formattedDateTime(DateTimeDecoder.decodeToDateTime(timestamp))
    }

    fun currentMilliseconds(): Long {
        return systemCurrentMilliseconds()
    }
}