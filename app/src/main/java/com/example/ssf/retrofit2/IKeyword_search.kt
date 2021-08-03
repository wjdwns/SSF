package com.example.ssf.retrofit2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IKeyword_search {
    @POST("/sign-up")
    //인풋을 정의
    fun requestKeyword_Search(
        @Body keywordsearch : Keyword_Search
    ) : Call<List<grant_form>> //아웃풋을 정의
}