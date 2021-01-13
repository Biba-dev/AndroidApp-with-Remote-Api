package com.example.faircorp

interface OnRoomSelectedListener {
    fun onRoomWindowsSelected(id: Long, name: String, current_temp: Double?, target_temp: Double?)
    fun onRoomSelected(id: Long)
}