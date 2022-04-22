package com.example.postviewer.network

import androidx.activity.compose.BackHandler
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .build()

interface PostApiService {
    @GET("posts")
    fun getPosts(): Call<String>
}

object MarsApi {
    val retrofitService : PostApiService by lazy {
        retrofit.create(PostApiService::class.java)
    }
}