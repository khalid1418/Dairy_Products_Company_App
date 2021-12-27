package com.example.dairyproductscompanyapp.dataLayer

import com.example.dairyproductscompanyapp.FakeData

interface product {

    suspend fun addProduct(product: FakeData)


}