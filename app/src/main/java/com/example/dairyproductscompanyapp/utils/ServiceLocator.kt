package com.example.dairyproductscompanyapp.utils

import com.example.dairyproductscompanyapp.dataLayer.CompanyFireStoreDataSource
import com.example.dairyproductscompanyapp.dataLayer.CompanyRepository
import com.example.dairyproductscompanyapp.domain.AddUseCase
import com.google.firebase.firestore.FirebaseFirestore


fun providerCompanyFireStoreDataSource():CompanyFireStoreDataSource = CompanyFireStoreDataSource(
    FirebaseFirestore.getInstance())

fun providerCompanyRepository():CompanyRepository = CompanyRepository(
    providerCompanyFireStoreDataSource())
fun ProviderUseCaseAddProduct():AddUseCase = AddUseCase(providerCompanyRepository())