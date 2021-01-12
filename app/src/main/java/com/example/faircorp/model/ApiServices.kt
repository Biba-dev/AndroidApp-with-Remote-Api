package com.example.faircorp.model

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServices {
    val windowsApiService : WindowApiService by lazy {
     Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("http://springapp-hiba-awad.cleverapps.io/api/") // this is the one i created with Spring
            .build()

       .create(WindowApiService::class.java)
    }
    val roomsApiService : RoomApiService by lazy {
        Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("http://springapp-hiba-awad.cleverapps.io/api/") // this is the one i created with Spring
                .build()
                .create(RoomApiService::class.java)
    }
    val buildingsApiService : BuildingApiService by lazy {
        Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("http://springapp-hiba-awad.cleverapps.io/api/") // this is the one i created with Spring
                .build()
                .create(BuildingApiService::class.java)
    }
}