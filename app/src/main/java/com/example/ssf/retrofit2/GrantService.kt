package com.example.ssf.retrofit2

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GrantService {
    @FormUrlEncoded
    @POST("/app_login/")
    fun requestLogin(

    ) : Call<List<grant_form>>

}

