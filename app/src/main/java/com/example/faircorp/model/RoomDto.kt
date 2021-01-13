package com.example.faircorp.model

data class RoomDto(val id: Long,
                   val name: String,
                   val currentTemperature: Double?,
                   val targetTemperature: Double?,
                   val floor: Int,
val windowslist: List<WindowDto>)