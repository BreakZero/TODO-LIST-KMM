package com.easy.todolist.core.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal val iosScope: CoroutineScope = object : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main
}

fun <T> Flow<T>.subscribe(
    onEach: (item: T) -> Unit,
    onComplete: () -> Unit,
    onThrow: (error: Throwable) -> Unit
): Job {
    return this
        .onEach { onEach(it) }
        .catch { onThrow(it) }
        .onCompletion { onComplete() }
        .launchIn(iosScope)
}

fun <T> (suspend () -> T).subscribe(
    scope: CoroutineScope,
    onSuccess: (result: T) -> Unit,
    onError: (error: Throwable) -> Unit
): Job {
    return scope.launch {
        try {
            onSuccess(this@subscribe.invoke())
        } catch (error: Throwable) {
            onError(error)
        }
    }
}