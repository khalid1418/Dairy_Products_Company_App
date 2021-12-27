package com.example.dairyproductscompanyapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dairyproductscompanyapp.addCompanyUi.AddViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return AddViewModel(ProviderUseCaseAddProduct()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}