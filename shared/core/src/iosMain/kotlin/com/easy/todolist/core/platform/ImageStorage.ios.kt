package com.easy.todolist.core.platform

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.refTo
import kotlinx.cinterop.usePinned
import kotlinx.cinterop.value
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import platform.Foundation.NSData
import platform.Foundation.NSDataReadingOptions
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSError
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSString
import platform.Foundation.NSUUID
import platform.Foundation.NSUserDomainMask
import platform.Foundation.create
import platform.Foundation.dataWithContentsOfFile
import platform.Foundation.getBytes
import platform.Foundation.stringByAppendingPathComponent
import platform.Foundation.writeToFile

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
actual class ImageStorage {
    private val fileManager = NSFileManager.defaultManager
    private val documentDirectory = NSSearchPathForDirectoriesInDomains(
        directory = NSDocumentDirectory,
        domainMask = NSUserDomainMask,
        expandTilde = true
    ).first() as NSString

    @OptIn(BetaInteropApi::class)
    actual suspend fun saveImage(bytes: ByteArray): String {
        return withContext(Dispatchers.Default) {
            val fileName = NSUUID.UUID().UUIDString + ".jpg"
            val fullPath = documentDirectory.stringByAppendingPathComponent(fileName)

            val data = bytes.usePinned {
                NSData.create(
                    bytes = it.addressOf(0),
                    length = bytes.size.toULong()
                )
            }

            data.writeToFile(
                path = fullPath,
                atomically = true
            )
            fileName
        }
    }

    @OptIn(BetaInteropApi::class)
    actual suspend fun getImage(fileName: String): ByteArray? {
        return withContext(Dispatchers.Default) {
            memScoped {
                val errorPtr: ObjCObjectVar<NSError?> = alloc()
                val fullPath = documentDirectory.stringByAppendingPathComponent(fileName)
                NSData.dataWithContentsOfFile(
                    path = fullPath,
                    options = NSDataReadingOptions.MIN_VALUE,
                    error = errorPtr.ptr
                )?.let { bytes ->
                    val array = ByteArray(bytes.length.toInt())
                    bytes.getBytes(array.refTo(0).getPointer(this), bytes.length)
                    return@withContext array
                }
                println(errorPtr.value?.description.orEmpty())
                return@withContext null
            }
        }
    }

    actual suspend fun deleteImage(fileName: String) {
        withContext(Dispatchers.Default) {
            val fullPath = documentDirectory.stringByAppendingPathComponent(fileName)
            fileManager.removeItemAtPath(fullPath, null)
        }
    }
}
