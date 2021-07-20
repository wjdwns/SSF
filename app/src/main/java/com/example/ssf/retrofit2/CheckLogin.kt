package com.example.ssf.retrofit2
//로그인 확인 아웃풋
data class CheckLogin(
    val isValid: Boolean,
    val nickname: String?,
    val birth: String?
)