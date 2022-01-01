package com.example.dairyproductscompanyapp.dataLayer

import com.example.dairyproductscompanyapp.model.CompanyDataModel

class CompanyRepository(
    private val companyFireStoreDataSource: CompanyFireStoreDataSource,
){
suspend fun addProduct(product: CompanyDataModel)=
    companyFireStoreDataSource.addProduct(product)

    suspend fun retrieveCompany() = companyFireStoreDataSource.retrieveCompanyData()

}