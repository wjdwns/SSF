package com.example.ssf.retrofit2

import retrofit2.Call
import retrofit2.http.POST

interface New_Service {
    @POST("/new")
    fun requestNew(
    ) : Call<List<grant_form>>
}