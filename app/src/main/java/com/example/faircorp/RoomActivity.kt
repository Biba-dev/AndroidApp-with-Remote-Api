package com.example.faircorp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.faircorp.model.ApiServices
import com.example.faircorp.model.RoomApiService
import com.example.faircorp.model.RoomDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RoomActivity : BasicActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var roomInfo : RoomDto
        val id = intent.getLongExtra(Room_NAME_PARAM, 0)
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().roomsApiService.findById(id).execute() }
                .onSuccess {
                    roomInfo = it.body()!!
                    runCatching {
                        ApiServices().roomsApiService.findById(roomInfo.id).execute()
                    }
                        .onSuccess {
                            roomInfo = it.body()!!
                            withContext(context = Dispatchers.Main) {

                                findViewById<TextView>(R.id.txt_room_name).text =
                                    roomInfo.name
                                findViewById<TextView>(R.id.txt_window_current_temperature).text =
                                    if (roomInfo.currentTemperature == null) "undefined" else roomInfo.currentTemperature.toString()
                                findViewById<TextView>(R.id.txt_window_target_temperature).text =
                                    if (roomInfo.targetTemperature == null) "undefined" else roomInfo.targetTemperature.toString()
                                findViewById<TextView>(R.id.txt_window_status).text =
                                    roomInfo.floor.toString()
                            }

                        }
                        .onFailure {
                            withContext(context = Dispatchers.Main) {
                                Toast.makeText(
                                    applicationContext,
                                    "Error on windows loading $it",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on rooms loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

    }

}
