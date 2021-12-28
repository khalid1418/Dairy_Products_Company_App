package com.example.dairyproductscompanyapp.domain

import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.dataLayer.CompanyRepository

class AddUseCase(private val addRepository: CompanyRepository) {

    suspend operator fun invoke(product: CompanyDataModel) = addRepository.addProduct(product)

}