package com.example.ssf.retrofit2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface CommentListService {
    @POST("/comment/list")
    fun requestCommentList(
        @Body CommentList : CommentList_Data
    ): Call<List<CommentList_Output>>
}
