package com.example.pilotDetail.repository

import com.example.pilotDetail.network.RetrofitClient
import com.example.pilotDetail.utils.Const.BASE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * The Repository.kt
 * Access point on the DB
 */

class NewsRepository private constructor() : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    ////API End pints
    fun getNewsArticles() =
        RetrofitClient.getInterfaceService(
            BASE_URL
        ).getPilots()

    fun getPilotDetail(id: Int) =
        RetrofitClient.getInterfaceService(
            BASE_URL
        ).getPilotDetails(id)

    companion object {
        private var instance: NewsRepository? = null
        fun getInstance(): NewsRepository {
            if (instance == null)
                instance =
                    NewsRepository()
            return instance!!
        }
    }
}