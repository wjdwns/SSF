package com.example.ssf.retrofit2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Cart_Del {
    @POST("/Like/Delete")
    fun requestCartdel(
        @Body Cartdel : Cart_Del_Data
    ):Call<Cart_Del_Output>
}