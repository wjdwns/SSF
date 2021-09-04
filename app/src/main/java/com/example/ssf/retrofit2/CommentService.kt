package com.example.ssf.retrofit2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CommentService {
    @POST("/comment/add")
    fun requestComment(
        @Body Comment_info : Comment_Data
    ):Call<Comment_Output>
}