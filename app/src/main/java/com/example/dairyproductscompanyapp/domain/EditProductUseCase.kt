package com.example.dairyproductscompanyapp.domain

import android.net.Uri
import com.example.dairyproductscompanyapp.dataLayer.CompanyRepository
import com.example.dairyproductscompanyapp.model.CompanyDataModel

class EditProductUseCase(private val editProductRepository: CompanyRepository) {
    suspend operator fun invoke(product:CompanyDataModel , id:String) = editProductRepository.editProduct(product , id)
}