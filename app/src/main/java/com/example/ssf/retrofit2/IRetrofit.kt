package com.example.ssf.retrofit2


import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET

interface IRetrofit {

    fun requestLogin(
        @Field("userid") userid: String,
        @Field("userpw") userpwd: String
    ) : Call<LoginData>
}