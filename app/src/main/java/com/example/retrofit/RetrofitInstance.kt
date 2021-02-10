package com.example.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    var retrofitInstance: Retrofit? = null

//        val client = OkHttpClient.Builder()
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(60, TimeUnit.SECONDS)
//            .build();

    fun getInstance(): Retrofit? {
        if (retrofitInstance == null) {
            retrofitInstance = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                //.client(client)
                .build()
        }
        return retrofitInstance
    }
}