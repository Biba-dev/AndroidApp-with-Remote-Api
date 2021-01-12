package com.example.faircorp

//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.faircorp.model.WindowApiService
import com.example.faircorp.model.WindowDto

import com.example.faircorp.model.WindowService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WindowActivity : BasicActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
// builder
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("http://springapp-hiba-awad.cleverapps.io/api/") // this is the one i created with Spring
            .build()
        //object to call methods
        val windowApiService = retrofit.create(WindowApiService::class.java)
        val mycall: Call<List<WindowDto>> = windowApiService.findAll()
        mycall.enqueue(object : Callback<List<WindowDto>> {
            override fun onFailure(call: Call<List<WindowDto>>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<WindowDto>>,
                response: Response<List<WindowDto>>
            ) {
                val Windows: List<WindowDto> = response.body()!!
//                val stringBuilder = StringBuilder()
                for (window in Windows) {
//                    stringBuilder.append(window.name)
                    findViewById<TextView>(R.id.txt_window_name).text = window.name
                    findViewById<TextView>(R.id.txt_room_name).text = window.room.name
                    findViewById<TextView>(R.id.txt_window_current_temperature).text =
                        window.room.currentTemperature?.toString()
                    findViewById<TextView>(R.id.txt_window_target_temperature).text =
                        window.room.targetTemperature?.toString()
                    findViewById<TextView>(R.id.txt_window_status).text = window.status.toString()
                }
            }
        })
        //###########################################
//        val param = intent.getStringExtra(WINDOW_NAME_PARAM)
//        val windowName = findViewById<TextView>(R.id.txt_window_name)
//        windowName.text = param
//
//        val windowService = WindowService()
//        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)
//        val window = WindowService().findById(id)
//
//        if (window != null) {
//            findViewById<TextView>(R.id.txt_window_name).text = window.name
//            findViewById<TextView>(R.id.txt_room_name).text = window.room.name
//            findViewById<TextView>(R.id.txt_window_current_temperature).text = window.room.currentTemperature?.toString()
//            findViewById<TextView>(R.id.txt_window_target_temperature).text = window.room.targetTemperature?.toString()
//            findViewById<TextView>(R.id.txt_window_status).text = window.status.toString()
//        }
    }

}