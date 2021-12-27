package com.example.dairyproductscompanyapp.dataLayer

import com.example.dairyproductscompanyapp.FakeData

class CompanyRepository(
    private val companyFireStoreDataSource: CompanyFireStoreDataSource,
){
suspend fun addProduct(product: FakeData)=
    companyFireStoreDataSource.addProduct(product)

}