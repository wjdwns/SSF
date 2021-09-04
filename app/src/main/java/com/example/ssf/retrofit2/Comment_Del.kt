package com.example.ssf.retrofit2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Comment_Del {
    @POST("/comment/delete")
    fun requestCommentdel(
        @Body Commentdel : Comment_Del_Data
    ): Call<Comment_Del_Output>
}