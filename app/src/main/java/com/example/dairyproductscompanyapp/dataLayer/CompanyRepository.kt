package com.example.dairyproductscompanyapp.dataLayer

import android.net.Uri
import com.example.dairyproductscompanyapp.model.CompanyDataModel

class CompanyRepository(
    private val companyFireStoreDataSource: CompanyFireStoreDataSource,
) {
    suspend fun addProduct(product: CompanyDataModel, image: Uri) =

        companyFireStoreDataSource.addProduct(product, image)

    suspend fun retrieveCompany() = companyFireStoreDataSource.retrieveCompanyData()

}