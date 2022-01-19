package com.example.dairyproductscompanyapp.orderProduct

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dairyproductscompanyapp.domain.OrderAddProductUseCase
import com.example.dairyproductscompanyapp.domain.RetrieveUseCase
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.model.OrderDataModel
import com.google.firebase.firestore.GeoPoint
import kotlinx.coroutines.launch
import java.text.NumberFormat

class OrderProductCompanyViewModel(private val orderAddProductUseCase: OrderAddProductUseCase):ViewModel() {

    var longtitude = MutableLiveData<String>()
    var littitude = MutableLiveData<String>()


    private fun insertOrderProduct(product: OrderDataModel){
        viewModelScope.launch {
            orderAddProductUseCase.invoke(product)
        }
    }

    private fun getNewProductEntry(
        quantity: String,
        buyerid: String,userId:String , refrence:String,nameProduct:String , price:String,

    ): OrderDataModel {
        return OrderDataModel(
            quantity = quantity.toInt(),
            buyerId = buyerid,
            userId,refrence,
            nameProduct = nameProduct,
            price =  price,
            location = littitude.value?.let { longtitude.value?.let { it1 -> GeoPoint(it.toDouble(), it1.toDouble()) } },

        )
    }

    fun addNewOrder(
        quantity: String,
        buyerid: String,userId: String , refrence: String , nameProduct: String , price: String

    ) {
        val newOrder = getNewProductEntry(quantity, buyerid ,userId  , refrence , nameProduct , price)
        insertOrderProduct(newOrder)
    }
}

