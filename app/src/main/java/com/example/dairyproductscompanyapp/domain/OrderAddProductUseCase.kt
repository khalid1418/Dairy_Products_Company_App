package com.example.dairyproductscompanyapp.domain

import com.example.dairyproductscompanyapp.dataLayer.CompanyRepository
import com.example.dairyproductscompanyapp.model.OrderDataModel

class OrderAddProductUseCase(private val addOrderRepository: CompanyRepository) {


    suspend operator fun invoke(product: OrderDataModel) = addOrderRepository.addOrder(product)
}