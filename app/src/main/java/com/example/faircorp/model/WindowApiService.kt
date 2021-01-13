package com.example.faircorp.model


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface WindowApiService {
    @GET("http://springapp-hiba-awad.cleverapps.io/api/windows")
    fun findAll(): Call<List<WindowDto>>

    @GET("http://springapp-hiba-awad.cleverapps.io/api/windows/{windows_id}")
    fun findById(@Path("windows_id") id: Long): Call<WindowDto>

    @PUT("http://springapp-hiba-awad.cleverapps.io/api/windows/{windows_id}/switch ")// /api/windows /{ windows _id} /switch
    fun updateWindowstatus(@Path("windows_id") id: Long, @Body window: WindowDto): Call<WindowDto>
}