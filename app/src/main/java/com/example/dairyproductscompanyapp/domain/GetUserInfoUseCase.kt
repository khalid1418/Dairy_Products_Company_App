package com.example.dairyproductscompanyapp.domain

import com.example.dairyproductscompanyapp.dataLayer.CompanyRepository

class GetUserInfoUseCase(private val getUserInfoRepository: CompanyRepository) {
    suspend operator fun invoke()=getUserInfoRepository.getInfoUser()
}