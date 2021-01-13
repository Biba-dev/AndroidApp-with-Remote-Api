package com.example.faircorp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RoomApiService {
    @GET("https://springapp-hiba-awad.cleverapps.io/api/rooms")
    fun findAll(): Call<List<RoomDto>>

    @GET("https://springapp-hiba-awad.cleverapps.io/api/rooms/{room_id}")
    fun findById(@Path("room_id") id: Long): Call<RoomDto>

    @GET("http://springapp-hiba-awad.cleverapps.io/api/rooms/{room_id}/switchWindow")
    fun switchRoomWindows(@Path("id") id: Long): Call<Void>

}