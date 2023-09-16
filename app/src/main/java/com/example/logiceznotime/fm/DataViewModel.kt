package com.example.logiceznotime.fm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.logiceznotime.model.CoinModel

class DataViewModel: ViewModel() {
    var mutableLiveData: MutableLiveData<MutableList<CoinModel>> = MutableLiveData()
}