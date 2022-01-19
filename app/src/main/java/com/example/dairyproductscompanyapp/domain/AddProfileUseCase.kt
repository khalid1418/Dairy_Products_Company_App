package com.example.dairyproductscompanyapp.domain

import com.example.dairyproductscompanyapp.dataLayer.CompanyRepository
import com.example.dairyproductscompanyapp.model.UserProfile

class AddProfileUseCase(private val addRepository: CompanyRepository) {
    suspend operator fun invoke(profile:UserProfile) = addRepository.addUser(profile)
}