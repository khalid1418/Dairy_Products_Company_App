package com.example.dairyproductscompanyapp.detailCompanyUi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel:ViewModel() {
    private var _quantity = MutableLiveData<Int>(0)
    val quantity: LiveData<Int> = _quantity

fun quantityplus(){
    _quantity.value= quantity.value?.plus(1)
}
    fun quantitydecrease(){
        _quantity.value=quantity.value?.minus(1)

    }

}