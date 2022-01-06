package com.example.dairyproductscompanyapp.orderProduct

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dairyproductscompanyapp.domain.OrderAddProductUseCase
import com.example.dairyproductscompanyapp.domain.RetrieveUseCase
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.model.OrderDataModel
import kotlinx.coroutines.launch

class OrderProductCompanyViewModel(private val orderAddProductUseCase: OrderAddProductUseCase):ViewModel() {



    private fun insertOrderProduct(product: OrderDataModel){
        viewModelScope.launch {
            orderAddProductUseCase.invoke(product)
        }
    }

    private fun getNewProductEntry(
        quantity: String,
        buyerid: String,userId:String

    ): OrderDataModel {
        return OrderDataModel(
            quantity = quantity.toInt(),
            buyerId = buyerid,
            userId

        )
    }

    fun addNewOrder(
        quantity: String,
        buyerid: String,userId: String

    ) {
        val newOrder = getNewProductEntry(quantity, buyerid ,userId )
        insertOrderProduct(newOrder)
    }
}

