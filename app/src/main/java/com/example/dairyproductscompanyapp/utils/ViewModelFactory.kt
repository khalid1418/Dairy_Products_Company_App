package com.example.dairyproductscompanyapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dairyproductscompanyapp.addCompanyUi.AddViewModel
import com.example.dairyproductscompanyapp.detailCompanyUi.DetailViewModel
import com.example.dairyproductscompanyapp.editProductUi.EditProductViewModel
import com.example.dairyproductscompanyapp.listCompanyUi.ListCompanyViewModel
import com.example.dairyproductscompanyapp.orderListUi.OrderListViewModel
import com.example.dairyproductscompanyapp.orderProduct.OrderProductCompanyViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(AddViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return AddViewModel(providerUseCaseAddProduct()) as T
            }
            modelClass.isAssignableFrom(ListCompanyViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return ListCompanyViewModel(providerUseCaseRetrieveCompany()) as T
            }
            modelClass.isAssignableFrom(OrderProductCompanyViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return OrderProductCompanyViewModel(providerUseCaseAddOrder()) as T
            }
            modelClass.isAssignableFrom(EditProductViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return EditProductViewModel(providerUseCaseEditProduct()) as T
            }
            modelClass.isAssignableFrom(OrderListViewModel::class.java) -> @Suppress("UNCHECKED_CAST")
            return OrderListViewModel(providerUseCaseRetrieveOrderBuyer()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}