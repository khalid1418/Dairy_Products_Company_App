package com.example.dairyproductscompanyapp.dataLayer

import android.net.Uri
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import kotlinx.coroutines.flow.Flow
import java.net.URI

interface CompanyDataSource {

    suspend fun addProduct(product: CompanyDataModel, imageURI: Uri)
    suspend fun retrieveCompanyData(): Flow<List<CompanyDataModel>>
//    suspend fun getCompanyDetails(product: CompanyDataModel, id:String)


}