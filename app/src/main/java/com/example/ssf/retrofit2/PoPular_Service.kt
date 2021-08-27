package com.example.ssf.retrofit2

import retrofit2.Call
import retrofit2.http.POST

interface PoPular_Service {
    @POST("/popular")
    fun requestPoPular(
    ) : Call<List<grant_form>>
}