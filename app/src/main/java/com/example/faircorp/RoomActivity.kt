package com.example.faircorp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.faircorp.model.*
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
//###############################################################################################
        // builder
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("http://springapp-hiba-awad.cleverapps.io/api/") // this is the one i created with Spring
            .build()
        //object to call methods
        val roomApiService  = retrofit.create(RoomApiService::class.java)
            val mycalls: Call<List<RoomDto>> = roomApiService.findAll()
        mycalls.enqueue(object : Callback<List<RoomDto>> {
            override fun onFailure(call: Call<List<RoomDto>>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }

            override fun onResponse(call: Call<List<RoomDto>>,response: Response<List<RoomDto>>) {
                val Rooms: List<RoomDto> = response.body()!!
//                val stringBuilder = StringBuilder()
                for (room in Rooms) {


                    findViewById<TextView>(R.id.txt_room_name).text = room.name
                    findViewById<TextView>(R.id.txt_room_windows).text = room.windowslist.toString()
                    findViewById<TextView>(R.id.txt_room_temperature).text = room.currentTemperature.toString()

                }
            }


        })

        //#########################################################################
//        val param = intent.getStringExtra(Room_NAME_PARAM)
//        val roomName = findViewById<TextView>(R.id.txt_room_name)
//        roomName.text = param
//
//        val windowService = WindowService()
//        val id = intent.getLongExtra(Room_NAME_PARAM, 0)
//        val room = RoomService().findById(id)

//        if (room != null) {
//            findViewById<TextView>(R.id.txt_room_name).text = room.name
//            findViewById<TextView>(R.id.txt_room_windows).text = room.windowslist.toString()
//            findViewById<TextView>(R.id.txt_room_temperature).text = room.temperature.toString()
//        }
    }

}
