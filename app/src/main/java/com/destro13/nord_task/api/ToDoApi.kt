package com.destro13.nord_task.api

import com.destro13.nord_task.model.ToDoResponse
import retrofit2.Response
import retrofit2.http.GET

interface ToDoApi {
    @GET("public-api/todos")
    suspend fun getToDoList(): Response<ToDoResponse>
}