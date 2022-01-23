package com.example.dairyproductscompanyapp.domain

import com.example.dairyproductscompanyapp.dataLayer.CompanyRepository

class RetrieveOrderBuyerUseCase(private val retrieveRepository: CompanyRepository) {

    suspend operator fun invoke() = retrieveRepository.retrieveOrderBuyer()
}