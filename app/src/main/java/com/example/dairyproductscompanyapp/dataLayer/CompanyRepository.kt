package com.example.dairyproductscompanyapp.dataLayer

import android.net.Uri
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.model.OrderDataModel

class CompanyRepository(
    private val companyFireStoreDataSource: CompanyFireStoreDataSource,
) {
    suspend fun addProduct(product: CompanyDataModel, image: Uri) =

        companyFireStoreDataSource.addProduct(product, image)

    suspend fun retrieveCompany() = companyFireStoreDataSource.retrieveCompanyData()

    suspend fun addOrder(product: OrderDataModel) = companyFireStoreDataSource.sendOrderProduct(product)

    suspend fun editProduct(product: CompanyDataModel , id:String , image: Uri?) = companyFireStoreDataSource.editProduct(product , id ,image )
    suspend fun retrieveOrderBuyer()=companyFireStoreDataSource.retrieveOrderBuyer()
    suspend fun deleteOrderDone(product: OrderDataModel)=companyFireStoreDataSource.deleteOrderDone(product)

}