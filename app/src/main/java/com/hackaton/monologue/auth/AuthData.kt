package com.hackaton.monologue.auth

data class LoginRequest(
    val id: String,
    val password: String
)

data class LoginResponse(
    val status: Int,
    val message: String,
    val data: TokenData
)

data class TokenData(
    val accessToken: String,
    val refreshToken: String
)

data class SignUpRequest(
    val id: String,
    val password: String,
    val name: String
)

data class SignUpResponse(
    val value: Int,
    val message: String
)