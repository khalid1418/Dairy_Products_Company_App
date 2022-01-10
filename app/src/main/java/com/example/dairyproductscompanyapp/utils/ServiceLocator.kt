package com.example.dairyproductscompanyapp.utils

import com.example.dairyproductscompanyapp.dataLayer.CompanyFireStoreDataSource
import com.example.dairyproductscompanyapp.dataLayer.CompanyRepository
import com.example.dairyproductscompanyapp.domain.*
import com.google.firebase.firestore.FirebaseFirestore


fun providerCompanyFireStoreDataSource(): CompanyFireStoreDataSource = CompanyFireStoreDataSource(
    FirebaseFirestore.getInstance()
)

fun providerCompanyRepository(): CompanyRepository = CompanyRepository(
    providerCompanyFireStoreDataSource()
)

fun providerUseCaseAddProduct(): AddUseCase = AddUseCase(providerCompanyRepository())

fun providerUseCaseRetrieveCompany(): RetrieveUseCase = RetrieveUseCase(providerCompanyRepository())

fun providerUseCaseAddOrder():OrderAddProductUseCase = OrderAddProductUseCase(
    providerCompanyRepository())
fun providerUseCaseEditProduct():EditProductUseCase = EditProductUseCase(providerCompanyRepository())
fun providerUseCaseRetrieveOrderBuyer():RetrieveOrderBuyerUseCase = RetrieveOrderBuyerUseCase(
    providerCompanyRepository())