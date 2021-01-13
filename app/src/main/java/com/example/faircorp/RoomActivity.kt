package com.example.faircorp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.faircorp.model.RoomApiService
import com.example.faircorp.model.RoomDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RoomActivity : BasicActivity() {

    lateinit var txtRoomName: TextView
    lateinit var txtRoomTemperature: TextView
    lateinit var txtFloor: TextView
    lateinit var txtTargetTemp: TextView
    lateinit var windowsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getLongExtra("room_id", 0)
        txtRoomName = findViewById<TextView>(R.id.txt_room_name)
        txtRoomTemperature = findViewById<TextView>(R.id.txt_room_temperature)
        txtFloor = findViewById<TextView>(R.id.txt_floor)
        txtTargetTemp = findViewById<TextView>(R.id.txt_target_temperature)
        windowsButton = findViewById<Button>(R.id.windows_button)
        windowsButton.setOnClickListener {
            var intent = Intent(Intent(this, WindowsActivity::class.java))
            intent.putExtra("room_id", id)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://springapp-hiba-awad.cleverapps.io/api/") // this is the one i created with Spring
            .build()
        //object to call methods
        val windowApiService = retrofit.create(RoomApiService::class.java)
        val mycall: Call<RoomDto> = windowApiService.findById(id)
        mycall.enqueue(object : Callback<RoomDto> {
            override fun onFailure(call: Call<RoomDto>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }

            override fun onResponse(
                call: Call<RoomDto>,
                response: Response<RoomDto>
            ) {
                setRoomData(response.body()!!)
            }
        })


    }

    fun setRoomData(roomDto: RoomDto) {
        txtRoomName.text = roomDto.name
        txtRoomTemperature.text =
            if (roomDto.currenttmp == null) "undefined" else roomDto.currenttmp.toString()
        txtTargetTemp.text =
            if (roomDto.tmp == null) "undefined" else roomDto.tmp.toString()
        txtFloor.text =
            roomDto.floor.toString()
    }

}
