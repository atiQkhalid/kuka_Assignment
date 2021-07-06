package com.example.pilotDetail.network

import com.example.pilotDetail.database.model.response.PilotDetailsDao
import com.example.pilotDetail.database.model.response.PilotResponseDao
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The APIInterface.kt
 */
interface ApiInterface {

    @GET("oguzayan/kuka/drivers")
    fun getPilots(): Call<PilotResponseDao>

    @GET("oguzayan/kuka/driverDetail")
    fun getPilotDetails(
        @Query("id") id: Int
    ): Call<PilotDetailsDao>
}
