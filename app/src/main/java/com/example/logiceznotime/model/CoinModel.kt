package com.example.logiceznotime.model

import com.google.gson.annotations.SerializedName

data class CoinModel(

    @SerializedName("chartName")
    var chartName: String,

    @SerializedName("time")
    var time: Time,

    @SerializedName("disclaimer")
    var disclaimer: String,

    @SerializedName("bpi")
    var bpi: Bpi,
    )

data class Time(
    @SerializedName("updated")
    var updated: String,
    @SerializedName("updatedISO")
    var updatedISO: String,
    @SerializedName("updateduk")
    var updateduk: String
)

data class Bpi(
    @SerializedName("USD")
    var USD: Currency,

    @SerializedName("GBP")
    var GBP: Currency,

    @SerializedName("EUR")
    var EUR: Currency
)

data class Currency(
    @SerializedName("code")
    var code: String,

    @SerializedName("symbol")
    var symbol: String,

    @SerializedName("rate")
    var rate: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("rate_float")
    var rate_float: Double
)