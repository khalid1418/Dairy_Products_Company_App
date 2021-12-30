package com.example.dairyproductscompanyapp.dataLayer

import com.example.dairyproductscompanyapp.model.CompanyDataModel
import kotlinx.coroutines.flow.Flow

interface CompanyDataSource {

    suspend fun addProduct(product: CompanyDataModel)
    suspend fun retrieveCompany(): Flow<CompanyDataModel>



}