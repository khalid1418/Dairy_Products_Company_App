package com.example.dairyproductscompanyapp.domain

import com.example.dairyproductscompanyapp.FakeData
import com.example.dairyproductscompanyapp.dataLayer.CompanyRepository
import com.example.dairyproductscompanyapp.dataLayer.product

class AddUseCase(private val addRepository: CompanyRepository) {

    suspend operator fun invoke(product: FakeData) = addRepository.addProduct(product)
}