package com.example.ssf.retrofit2

data class CheckLogin(
    val isValid: Boolean,
    val nickname: String?,
    val birth: String?
)