package com.example.faircorp.model


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

 interface WindowApiService {
    @GET("windows")
    fun findAll(): Call<List<WindowDto>>

    @GET("windows /{ windows _id} ")
    fun findById(@Path("windows _id") id: Long): Call<WindowDto>

    @PUT("windows /{ windows _id} /switch ")// /api/windows /{ windows _id} /switch
    fun updateWindowstatus(@Path("windows _id") id: Long, @Body window: WindowDto): Call<WindowDto>
}