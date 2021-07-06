package com.example.pilotDetail.viewmodels

import com.example.pilotDetail.base.BaseViewModel
import com.example.pilotDetail.database.model.response.PilotDetailsDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *  HomeViewModel.kt
 */
class DetailViewModel : BaseViewModel<DetailViewModel.View>() {

    fun getPilotDetail(id: Int) {
        getView().showProgressBar()
        newsRepository.getPilotDetail(id)
            .enqueue(object : Callback<PilotDetailsDao> {
                override fun onResponse(
                    call: Call<PilotDetailsDao>,
                    response: Response<PilotDetailsDao>
                ) {
                    getView().dismissProgressBar()
                    response.run {
                        if (isSuccessful) {
                            body()?.run {
                                var age = this.age
                                var team = this.team
                                var image = this.image
                            } ?: getView().onUpdateUser("Something went wrong")
                        }
                    }
                }

                override fun onFailure(call: Call<PilotDetailsDao>, t: Throwable) {
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