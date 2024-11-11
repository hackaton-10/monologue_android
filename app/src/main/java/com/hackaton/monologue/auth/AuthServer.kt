package com.hackaton.monologue.auth

import android.util.Log
import com.hackaton.monologue.ApiProvider
import com.hackaton.monologue.utill.accessToken
import com.hackaton.monologue.utill.refreshToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val apiProvider = ApiProvider.getRetrofit().create(AuthApi::class.java)

fun login(loginRequest: LoginRequest, callback: (Int) -> Unit) {
    apiProvider.signIn(loginRequest).enqueue(object : Callback<LoginResponse> {
        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
            if (response.isSuccessful) {
                val res = response.body()
                if (res != null) {
                    accessToken = res.data.accessToken
                    refreshToken = res.data.refreshToken
                    callback(res.status)
                }
            }
            callback(response.code())
        }
        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            Log.d("server", t.message.toString())
        }
    })
}

fun signUp(signUpRequest: SignUpRequest, callback: (Int) -> Unit) {
    apiProvider.signUp(signUpRequest).enqueue(object : Callback<SignUpResponse> {
        override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
            callback(response.code())
            Log.d("server", response.code().toString())
        }

        override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
            Log.d("server", t.message.toString())
        }
    })
}