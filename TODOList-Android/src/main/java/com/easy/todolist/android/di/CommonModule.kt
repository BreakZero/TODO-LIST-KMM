package com.easy.todolist.android.di

import android.app.Activity
import com.easy.todolist.android.common.ImagePicker
import com.easy.todolist.android.common.decoder.StringDecoder
import com.easy.todolist.android.common.decoder.UriDecoder
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonModule = module {
    singleOf(::UriDecoder) bind StringDecoder::class
    factory {(activity: Activity) ->
        ImagePicker(activity)
    }
}
