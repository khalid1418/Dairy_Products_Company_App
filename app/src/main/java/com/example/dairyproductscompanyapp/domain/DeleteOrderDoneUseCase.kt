package com.example.dairyproductscompanyapp.domain

import com.example.dairyproductscompanyapp.dataLayer.CompanyRepository
import com.example.dairyproductscompanyapp.model.OrderDataModel

class DeleteOrderDoneUseCase(private val deleteOrderDoneRepository: CompanyRepository) {
    suspend operator fun invoke(product:OrderDataModel)=deleteOrderDoneRepository.deleteOrderDone(product)
}