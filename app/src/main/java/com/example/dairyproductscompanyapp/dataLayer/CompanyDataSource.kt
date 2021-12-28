package com.example.dairyproductscompanyapp.dataLayer

import com.example.dairyproductscompanyapp.model.CompanyDataModel

interface CompanyDataSource {

    suspend fun addProduct(product: CompanyDataModel)
    suspend fun retrieveCompany(company:CompanyDataModel)



}