package com.example.dairyproductscompanyapp.dataLayer

import android.net.Uri
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.model.OrderDataModel
import kotlinx.coroutines.flow.Flow
import java.net.URI

interface CompanyDataSource {

    suspend fun addProduct(product: CompanyDataModel, imageURI: Uri)
    suspend fun retrieveCompanyData(): Flow<List<CompanyDataModel>>
suspend fun sendOrderProduct(product: OrderDataModel)
suspend fun editProduct(product: CompanyDataModel , id:String)

}