package com.example.ssf.retrofit2

import retrofit2.Call
import retrofit2.http.*

interface LoginService {
    @POST("/login")
    fun requestLogin(
        @Body Login:login_Data
    ) : Call<CheckLogin>
}