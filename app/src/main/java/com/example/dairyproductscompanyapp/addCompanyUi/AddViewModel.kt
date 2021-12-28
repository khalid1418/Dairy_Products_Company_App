package com.example.dairyproductscompanyapp.addCompanyUi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.domain.AddUseCase
import kotlinx.coroutines.launch

class AddViewModel(private val addUseCase: AddUseCase):ViewModel() {





     private fun insertProduct(product: CompanyDataModel){
        viewModelScope.launch{
            addUseCase.invoke(product)
        }

    }
    private fun getNewProductEntry(Name:String, phone:String, image:String, NameProduct:String, price:String ): CompanyDataModel {
        return CompanyDataModel(nameCompany = Name , phone = phone.toInt() , image = image , nameProduct = NameProduct , price = price.toInt())
    }
    fun addNewProduct(Name:String ,phone:String ,image:String , NameProduct:String ,  price:String){
        val newProduct = getNewProductEntry(Name , phone ,image , NameProduct , price)
        insertProduct(newProduct)
    }
    fun isEntryValid(name:String ,phone: String , image: String , nameProduct:String ,price: String ):Boolean {
        if (name.isBlank() || phone.isBlank() || image.isBlank() || nameProduct.isBlank() || price.isBlank()) {
            return false
        }
        return true
    }

}