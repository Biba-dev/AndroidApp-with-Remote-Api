package com.example.faircorp.model

enum class Status { OPEN, CLOSED }

data class WindowDto(
    val id: Long,
    val name: String,
    val roomName: String,
    val windowStatus: String,
    var roomId: Long
)