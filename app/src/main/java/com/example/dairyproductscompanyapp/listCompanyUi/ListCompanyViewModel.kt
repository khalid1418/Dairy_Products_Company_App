package com.example.dairyproductscompanyapp.listCompanyUi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dairyproductscompanyapp.domain.RetrieveUseCase
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import kotlinx.coroutines.launch

class ListCompanyViewModel(private val retrieveUseCase: RetrieveUseCase):ViewModel() {

//     fun retrieveCompany(company:CompanyDataModel){
//        viewModelScope.launch {
//            retrieveUseCase.invoke(company)
//        }
//    }



}