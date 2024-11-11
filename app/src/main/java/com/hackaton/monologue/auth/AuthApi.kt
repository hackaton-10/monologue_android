package com.hackaton.monologue.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/sign-in")
    fun signIn(@Body request: LoginRequest): Call<LoginResponse>

    @POST("/auth/sign-up")
    fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>
}