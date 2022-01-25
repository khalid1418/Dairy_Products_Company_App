package com.example.dairyproductscompanyapp.addCompanyUi

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.domain.AddUseCase
import kotlinx.coroutines.launch

class AddViewModel(private val addUseCase: AddUseCase) : ViewModel() {


    private fun insertProduct(product: CompanyDataModel, image: Uri) {
        viewModelScope.launch {
            addUseCase.invoke(product, image)
        }

    }

    private fun getNewProductEntry(
        Name: String,
        phone: String,
        NameProduct: String,
        price: String,
        imageView: String, id: String
    ): CompanyDataModel {
        return CompanyDataModel(
            nameCompany = Name,
            phone = phone.toInt(),
            nameProduct = NameProduct,
            price = price.toDouble(),
            image = imageView,
            userid = id
        )

    }

    fun addNewProduct(
        Name: String,
        phone: String,
        NameProduct: String,
        price: String,
        imageView: String,
        imageUri: Uri, id: String
    ) {
        val newProduct = getNewProductEntry(Name, phone, NameProduct, price, imageView, id)
        insertProduct(newProduct, imageUri)
    }

    fun isEntryValid(
        name: String,
        phone: String,
        nameProduct: String,
        price: String,
        imageView: String,image: Uri?
    ): Boolean {
        if (name.isBlank() || phone.isBlank() || nameProduct.isBlank() || price.isBlank() || imageView.isBlank() || image == null) {
            return false
        }
        return true
    }

}