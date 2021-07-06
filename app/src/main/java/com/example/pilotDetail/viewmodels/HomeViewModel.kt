package com.example.pilotDetail.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.pilotDetail.base.BaseViewModel
import com.example.pilotDetail.database.model.response.ItemsItem
import com.example.pilotDetail.database.model.response.PilotDetailsDao
import com.example.pilotDetail.database.model.response.PilotResponseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

/**
 *  HomeViewModel.kt
 */
class HomeViewModel : BaseViewModel<HomeViewModel.View>() {

    private val pilotList = MutableLiveData<List<ItemsItem>>()

    val pilotListData = MediatorLiveData<List<ItemsItem>>().apply {
        addSource(pilotList) { value -> this.setValue(value) }
    }

    fun getNewsArticles() {
        getView().showProgressBar()
        newsRepository.getNewsArticles()
            .enqueue(object : Callback<PilotResponseDao> {
                override fun onResponse(
                    call: Call<PilotResponseDao>,
                    response: Response<PilotResponseDao>
                ) {
                    getView().dismissProgressBar()
                    response.run {
                        if (isSuccessful) {
                            body()?.run {
                                this.items?.let {
                                    pilotList.value = it
                                } ?: getView().onUpdateUser("Something went wrong")
                            } ?: getView().onUpdateUser("Something went wrong")
                        }
                    }
                }

                override fun onFailure(call: Call<PilotResponseDao>, t: Throwable) {
                    getView().dismissProgressBar()
                    getView().onUpdateUser(t.message.toString())
                }
            })
    }

    interface View {
        fun onUpdateUser(message: String)
        fun showProgressBar()
        fun dismissProgressBar()
    }
}