package com.hackaton.monologue

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    private const val BASE_URL = "http://13.209.111.163:8080"

    private val instance: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    fun getRetrofit(): Retrofit {
        return instance
    }
}