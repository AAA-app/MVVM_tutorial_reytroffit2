package com.aaa.mvvm_tutorial_reytroffit2.network

import com.aaa.mvvm_tutorial_reytroffit2.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("repositories") // repositories?q=newyork
    fun getDataFromAPI(@Query("q") query: String) : Call<RecyclerList>
}