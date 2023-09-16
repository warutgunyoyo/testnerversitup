package com.example.logiceznotime.service

import com.example.logiceznotime.model.CoinModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CoinApi {
    @GET("/v1/bpi/currentprice.json")
    suspend  fun getBitcoinPrice(): Response<CoinModel>
}