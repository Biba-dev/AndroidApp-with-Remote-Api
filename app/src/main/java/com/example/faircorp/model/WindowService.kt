package com.example.faircorp.model

import android.app.Service
import android.content.Intent
import android.os.IBinder
import retrofit2.Call

class WindowService : Service() {
// I deleted the static data because I want to use my remote API
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
// Static Data
//    companion object {
//        // Fake rooms
//        val ROOMS: List<RoomDto> = listOf(
//            RoomDto(1, "Room EF 6.10", 18.2, 20.0),
//            RoomDto(2, "Hall", 18.2, 18.0),
//            RoomDto(3, "Room EF 7.10", 21.2, 20.0)
//        )
//
//        // Fake lights
//        val WINDOWS: List<WindowDto> = listOf(
//            WindowDto(1, "Entry Window", ROOMS[0], Status.CLOSED),
//            WindowDto(2, "Back Window", ROOMS[0], Status.CLOSED),
//            WindowDto(3, "Sliding door", ROOMS[1], Status.OPEN),
//            WindowDto(4, "Window 1", ROOMS[2], Status.CLOSED),
//            WindowDto(5, "Window 2", ROOMS[2], Status.CLOSED)
//        )
//    }
    //deleted for using my remote API


}
