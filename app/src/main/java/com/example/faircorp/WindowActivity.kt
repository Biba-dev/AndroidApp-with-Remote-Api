package com.example.faircorp


import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.faircorp.model.RoomDto
import com.example.faircorp.model.WindowApiService
import com.example.faircorp.model.WindowDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WindowActivity : BasicActivity() {

    lateinit var txtWindowId: TextView
    lateinit var txtWindowName: TextView
    lateinit var txtWindowStatus: TextView
    lateinit var txtRoomName: TextView
    lateinit var txtRoomId: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        txtWindowId = findViewById<TextView>(R.id.txt_window_name)
        txtWindowName = findViewById<TextView>(R.id.txt_room_name)
        txtWindowStatus = findViewById<TextView>(R.id.txt_window_current_temperature)
        txtRoomName = findViewById<TextView>(R.id.txt_window_target_temperature)
        txtRoomId = findViewById<TextView>(R.id.txt_window_status)


        var roomInfo: RoomDto
        var windowInfo: WindowDto
        val id = intent.getLongExtra("window_id", 0)


        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("http://springapp-hiba-awad.cleverapps.io/api/") // this is the one i created with Spring
            .build()
        //object to call methods
        val windowApiService = retrofit.create(WindowApiService::class.java)
        val mycall: Call<WindowDto> = windowApiService.findById(id)
        mycall.enqueue(object : Callback<WindowDto> {
            override fun onFailure(call: Call<WindowDto>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }

            override fun onResponse(
                call: Call<WindowDto>,
                response: Response<WindowDto>
            ) {
                setWindowData(response.body()!!)
            }
        })


    }

    fun setWindowData(window: WindowDto) {
        txtWindowId.text = window.id.toString()
        txtWindowName.text = window.name
        txtWindowStatus.text = window.windowStatus
        txtRoomName.text = window.roomName
        txtRoomId.text = window.roomId.toString()

    }

}