package com.example.dairyproductscompanyapp.profileUserUi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.dairyproductscompanyapp.domain.GetUserInfoUseCase
import com.example.dairyproductscompanyapp.model.UserProfile
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserProfileViewModel(private val getUserInfoUseCase: GetUserInfoUseCase):ViewModel() {

    private val _userInfo = MutableStateFlow(UserProfile())

    var userInfo:StateFlow<UserProfile> = _userInfo.asStateFlow()
    var userInfoLiveData = userInfo.asLiveData()

    init {
        getUserInfo()
    }

     fun getUserInfo(){
        viewModelScope.launch {
            getUserInfoUseCase.invoke().collect{
                _userInfo.value = it
                Log.e("TAG", "getUserInfo:$it ", )
            }


        }
    }
}