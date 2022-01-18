package com.example.dairyproductscompanyapp.editProfileUi

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dairyproductscompanyapp.domain.EditProfileUseCase
import com.example.dairyproductscompanyapp.model.UserProfile
import kotlinx.coroutines.launch

class EditProfileViewModel(private val editProfileUseCase: EditProfileUseCase):ViewModel() {


    fun editProfile(profile: UserProfile , image:Uri?){
        viewModelScope.launch {
            editProfileUseCase.invoke(profile , image)
        }
    }
    private fun getOldProfile(
        Name: String,
        email: String,
        birth: String,
        image: String
    ): UserProfile {
        return UserProfile(
            userName = Name,
            userEmail = email,
            userBirth = birth,
            imageView = image
        )
    }

    fun setNewProfile(
        Name: String,
        email: String,
        birth: String,
        image: String,
        imageView:Uri?
    ) {
        val newProduct = getOldProfile(Name, email, birth , image)
        editProfile(newProduct , imageView)
    }
    fun isEntryValid(
        name: String,
        email: String,
        birth: String
    ): Boolean {
        if (name.isBlank() || email.isBlank() || birth.isBlank()) {
            return false
        }
        return true
    }
}