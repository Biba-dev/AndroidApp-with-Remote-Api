package com.example.faircorp.model

import android.app.Service
import android.content.Intent
import android.os.IBinder

class WindowService : Service() {
    // I deleted the static data because I want to use my remote API
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }


}
