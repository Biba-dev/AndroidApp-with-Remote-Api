package com.example.faircorp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BuildingApiService {
    @GET("http://springapp-hiba-awad.cleverapps.io/api/building")
    fun findAll(): Call<List<BuildingDto>>

    @GET("http://springapp-hiba-awad.cleverapps.io/api/building/{id}")
    fun findById(@Path("id") id: Long): Call<BuildingDto>
}