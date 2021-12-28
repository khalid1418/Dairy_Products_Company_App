package com.example.dairyproductscompanyapp.domain

import com.example.dairyproductscompanyapp.dataLayer.CompanyRepository
import com.example.dairyproductscompanyapp.model.CompanyDataModel

class RetrieveUseCase(private val retrieveRepository: CompanyRepository) {

    suspend operator fun invoke(company: CompanyDataModel){
        retrieveRepository.addProduct(company)
    }
}