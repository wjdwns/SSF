package com.example.ssf.retrofit2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SearchSetting {
    @POST("/Search/select")
    fun requestSelect(
        @Body Search : SearchSetting_Data
    ) : Call<List<grant_form>>
}