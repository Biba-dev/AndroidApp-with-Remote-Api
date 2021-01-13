package com.example.faircorp.model

data class RoomDto(
    val id: Long,
    val name: String,
    val currenttmp: Double?,
    val tmp: Double?,
    val floor: Int,
    val building: Long?
)