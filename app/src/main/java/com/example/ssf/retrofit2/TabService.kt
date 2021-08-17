package com.example.ssf.retrofit2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TabService {
    @POST("/Like/Update")
    fun requestTab(
        @Body Tab :Tab_Data
    ) : Call<Tab_Output>
}