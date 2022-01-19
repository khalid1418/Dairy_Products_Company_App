package com.example.dairyproductscompanyapp.domain

import android.net.Uri
import com.example.dairyproductscompanyapp.dataLayer.CompanyRepository
import com.example.dairyproductscompanyapp.model.UserProfile

class EditProfileUseCase(private val editProfileRepository: CompanyRepository) {
    suspend operator fun invoke(profile:UserProfile , image: Uri?) =editProfileRepository.editUserProfile(profile , image)
}