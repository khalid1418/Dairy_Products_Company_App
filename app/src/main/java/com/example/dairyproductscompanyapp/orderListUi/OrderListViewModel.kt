package com.example.dairyproductscompanyapp.orderListUi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.dairyproductscompanyapp.domain.RetrieveOrderBuyerUseCase
import com.example.dairyproductscompanyapp.model.OrderDataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderListViewModel(private val orderUseCase:RetrieveOrderBuyerUseCase):ViewModel() {


    //
    private val _product = MutableStateFlow<List<OrderDataModel>>(emptyList())

    //
    var product: StateFlow<List<OrderDataModel>> = _product.asStateFlow()

//   private val _companyLiveData = MutableLiveData<List<CompanyDataModel>>()

    val orderLiveData = product.asLiveData()

    init {
        retrieveOrderCompany()
    }


    private fun retrieveOrderCompany() {
        viewModelScope.launch {
            orderUseCase.invoke().collect { orderInfo ->

                _product.update { orderInfo }
                Log.e("TAG", "idk::${orderInfo}")

            }


        }
        Log.e("TAG", "LoopTime:${product.value}")
    }
}