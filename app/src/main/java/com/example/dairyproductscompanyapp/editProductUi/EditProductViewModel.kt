package com.example.dairyproductscompanyapp.editProductUi

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dairyproductscompanyapp.domain.EditProductUseCase
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import kotlinx.coroutines.launch

class EditProductViewModel(private val editProductUseCase:EditProductUseCase):ViewModel() {

     fun editProduct(product: CompanyDataModel , id:String , image:Uri?) {
        viewModelScope.launch {
            editProductUseCase.invoke(product , id , image)
        }

    }

    private fun getOldProductEntry(
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
            price = price.toInt(),
            image = imageView,
            userid = id
        )
    }

    fun setNewProduct(
        Name: String,
        phone: String,
        NameProduct: String,
        price: String,
        imageView: String,
         id: String ,refrance:String ,image: Uri?
    ) {
        val newProduct = getOldProductEntry(Name, phone, NameProduct, price, imageView, id)
        editProduct(newProduct , refrance , image)
    }

    fun isEntryValid(
        name: String,
        phone: String,
        nameProduct: String,
        price: String,
        imageView: String
    ): Boolean {
        if (name.isBlank() || phone.isBlank() || nameProduct.isBlank() || price.isBlank() || imageView.isBlank()) {
            return false
        }
        return true
    }


}