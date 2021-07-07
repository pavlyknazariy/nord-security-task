package com.destro13.nord_task.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object CoroutineContextProvider: DispatcherProvider {
    override fun main() = Dispatchers.Main
    override fun computation() = Dispatchers.Default
    override fun io() = Dispatchers.IO
    override fun unconfined() = Dispatchers.Unconfined
}

suspend fun <T> withMainContext(
    block: suspend CoroutineScope.() -> T
): T = withContext(CoroutineContextProvider.main(), block)

suspend fun <T> withComputationContext(
    block: suspend CoroutineScope.() -> T
): T = withContext(CoroutineContextProvider.computation(), block)

suspend fun <T> withIoContext(
    block: suspend CoroutineScope.() -> T
): T = withContext(CoroutineContextProvider.io(), block)

suspend fun <T> withUnconfinedContext(
    block: suspend CoroutineScope.() -> T
): T = withContext(CoroutineContextProvider.unconfined(), block)
