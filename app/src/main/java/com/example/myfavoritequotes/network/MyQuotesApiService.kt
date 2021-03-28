package com.example.myfavoritequotes.network

import com.example.myfavoritequotes.data.MyQuoteModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val BASE_URL = "https://zenquotes.io/api/"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MyQuotesApiService {
    @GET("random")
    suspend fun getQuote() : List<MyQuoteModel>
}

object MyQuotesApi {
    val retrofitService : MyQuotesApiService by lazy {
        retrofit.create(MyQuotesApiService::class.java)
    }
}