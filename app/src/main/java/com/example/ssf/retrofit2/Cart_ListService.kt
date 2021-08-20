package com.example.ssf.retrofit2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Cart_ListService {
    @POST("/Like/Update")
    fun requestCart(
        @Body User :Int
    ) : Call<List<Cart_Output>>
}