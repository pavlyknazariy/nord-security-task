package com.destro13.nord_task.coroutine

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun computation(): CoroutineDispatcher

    fun io(): CoroutineDispatcher

    fun main(): CoroutineDispatcher

    fun unconfined(): CoroutineDispatcher
}