package com.example.dairyproductscompanyapp.listCompanyUi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dairyproductscompanyapp.domain.RetrieveUseCase
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListCompanyViewModel(private val retrieveUseCase: RetrieveUseCase):ViewModel() {

//    var _company = MutableStateFlow(CompanyDataModel())
//    val company:StateFlow<CompanyDataModel> = _company.asStateFlow()


     fun retrieveCompany(){
        viewModelScope.launch {
            retrieveUseCase.invoke()
        }
    }



}