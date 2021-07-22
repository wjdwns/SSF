package com.example.ssf.retrofit2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IJoinMember {
    @POST("/sign-up")
    //인풋을 정의
    fun requestJoinMember(
        @Body JoinMember : JoinMember_Data
    ) : Call<JoinMember> //아웃풋을 정의



}