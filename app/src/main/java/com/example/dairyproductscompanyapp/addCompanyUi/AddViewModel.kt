package com.example.dairyproductscompanyapp.addCompanyUi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dairyproductscompanyapp.FakeData
import com.example.dairyproductscompanyapp.dataLayer.CompanyRepository
import com.example.dairyproductscompanyapp.dataLayer.product
import com.example.dairyproductscompanyapp.domain.AddUseCase
import kotlinx.coroutines.launch

class AddViewModel(private val addUseCase: AddUseCase):ViewModel() {

    val nameCompany = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<Int>()
    val nameProduct = MutableLiveData<String>()
    val price = MutableLiveData<Int>()



     private fun insertProduct(product: FakeData){
        viewModelScope.launch{
            addUseCase.invoke(product)
        }

    }
    private fun getNewProductEntry(Name:String, phone:String, image:String, NameProduct:String, price:String ):FakeData{
        return FakeData(nameCompany = Name , phone = phone.toInt() , image = image , nameProduct = NameProduct , price = price.toInt())
    }
    fun addNewProduct(Name:String ,phone:String ,image:String , NameProduct:String ,  price:String){
        val newProduct = getNewProductEntry(Name , phone ,image , NameProduct , price)
        insertProduct(newProduct)
    }
}