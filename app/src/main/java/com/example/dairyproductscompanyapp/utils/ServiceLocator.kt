package com.example.dairyproductscompanyapp.utils

import com.example.dairyproductscompanyapp.dataLayer.CompanyFireStoreDataSource
import com.example.dairyproductscompanyapp.dataLayer.CompanyRepository
import com.example.dairyproductscompanyapp.domain.AddUseCase
import com.example.dairyproductscompanyapp.domain.RetrieveUseCase
import com.google.firebase.firestore.FirebaseFirestore


fun providerCompanyFireStoreDataSource(): CompanyFireStoreDataSource = CompanyFireStoreDataSource(
    FirebaseFirestore.getInstance()
)

fun providerCompanyRepository(): CompanyRepository = CompanyRepository(
    providerCompanyFireStoreDataSource()
)

fun providerUseCaseAddProduct(): AddUseCase = AddUseCase(providerCompanyRepository())

fun providerUseCaseRetrieveCompany(): RetrieveUseCase = RetrieveUseCase(providerCompanyRepository())