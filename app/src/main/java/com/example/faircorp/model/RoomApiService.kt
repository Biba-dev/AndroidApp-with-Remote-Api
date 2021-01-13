package com.example.faircorp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RoomApiService {
    @GET("rooms")
    fun findAll(): Call<List<RoomDto>>

    @GET("rooms/{room_id} ")
    fun findById(@Path("room_id") id: Long): Call<RoomDto>

    @GET("rooms/{room_id}/switchWindows")
    fun switchRoomWindows(@Path("id") id: Long): Call<Void>


}