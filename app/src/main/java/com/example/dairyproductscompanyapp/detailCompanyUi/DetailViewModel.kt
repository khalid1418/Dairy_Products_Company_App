package com.example.dairyproductscompanyapp.detailCompanyUi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dairyproductscompanyapp.domain.OrderAddProductUseCase
import com.example.dairyproductscompanyapp.model.OrderDataModel
import kotlinx.coroutines.launch

class DetailViewModel():ViewModel() {
    private var _quantity = MutableLiveData<Int>(0)
    val quantity: LiveData<Int> = _quantity

fun quantityplus(){
    _quantity.value= quantity.value?.plus(1)
}
    fun quantitydecrease(){
        if (_quantity.value!! > 0) {
            _quantity.value = quantity.value?.minus(1)
        }else{

        }

    }


}

