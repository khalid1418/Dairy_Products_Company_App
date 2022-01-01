package com.example.dairyproductscompanyapp.listCompanyUi

import android.util.Log
import androidx.lifecycle.*
import androidx.loader.content.Loader
import com.example.dairyproductscompanyapp.domain.RetrieveUseCase
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.utils.providerUseCaseAddProduct
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ListCompanyViewModel(private val retrieveUseCase: RetrieveUseCase) : ViewModel() {

    var _company = MutableStateFlow((MutableStateFlow<MutableList<CompanyDataModel>>(mutableListOf(
        CompanyDataModel("khalid" , 2,"https://iconape.com/wp-content/files/uf/366551/png/366551.png")
    ))))
    val company: StateFlow<MutableList<CompanyDataModel>> = _company.value



    fun retrieveCompany() {
        viewModelScope.launch {
            retrieveUseCase.invoke().collect { companyInfo ->
                if (!_company.value.value.contains(companyInfo)) {
                    _company.value.value.add(companyInfo)
                }








            }
                Log.e("TAG", "LoopTime:${company.value}")
            }
        }
    }
