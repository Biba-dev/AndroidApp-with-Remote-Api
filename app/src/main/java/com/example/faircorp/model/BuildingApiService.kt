package com.example.faircorp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BuildingApiService {
    @GET("building")
    fun findAll(): Call<List<BuildingDto>>

    @GET("building/{id}")
    fun findById(@Path("id") id: Long): Call<BuildingDto>
}