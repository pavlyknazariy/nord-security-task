package com.destro13.nord_task.api

import com.destro13.nord_task.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ToDoApiFactory {
    var SERVER_URL = BuildConfig.API_URL

    fun create(gson: Gson, client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
}