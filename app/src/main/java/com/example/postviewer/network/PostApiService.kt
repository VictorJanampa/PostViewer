package com.example.postviewer.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PostApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("comments?postId={postId}")
    suspend fun getPostComments(@Query("postId") PostId: Int): List<Post>
}

object PostApi {
    val retrofitService : PostApiService by lazy {
        retrofit.create(PostApiService::class.java)
    }
}