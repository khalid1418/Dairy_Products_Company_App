package com.example.dairyproductscompanyapp.listCompanyUi

import android.util.Log
import androidx.lifecycle.*
import androidx.loader.content.Loader
import com.example.dairyproductscompanyapp.domain.AddProfileUseCase
import com.example.dairyproductscompanyapp.domain.RetrieveUseCase
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.model.UserProfile
import com.example.dairyproductscompanyapp.utils.providerUseCaseAddProduct
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ListCompanyViewModel(private val retrieveUseCase: RetrieveUseCase , private val addProfileUseCase: AddProfileUseCase) : ViewModel() {
    //
    private val _company = MutableStateFlow<List<CompanyDataModel>>(emptyList())

    //
    var company: StateFlow<List<CompanyDataModel>> = _company.asStateFlow()

//   private val _companyLiveData = MutableLiveData<List<CompanyDataModel>>()

    val companyLiveData = company.asLiveData()

    init {
        retrieveCompany()
    }


    private fun retrieveCompany() {
        viewModelScope.launch {
            retrieveUseCase.invoke().collect { companyInfo ->

                _company.update { companyInfo }
                Log.e("TAG", "before::${companyInfo}")

            }


        }
        Log.e("TAG", "LoopTime:${company.value}")
    }
     fun addProfile(profile:UserProfile){
        viewModelScope.launch {
            addProfileUseCase.invoke(profile)
        }
    }
}

