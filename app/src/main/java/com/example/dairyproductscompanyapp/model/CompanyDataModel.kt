package com.example.dairyproductscompanyapp.model

import androidx.core.location.LocationRequestCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.ktx.Firebase
import java.lang.ref.Reference


data class CompanyDataModel(
    val nameCompany:String ="",
    val phone:Int =0,
    var image: String = "",
    val nameProduct:String = "",
    val price:Int = 0,
    val userid:String="",
    var reference:String =""

)

data class OrderDataModel(
    val quantity:Int =0 ,
    val buyerId:String ="",
    val sellerId:String="",
    val reference:String="",
    val nameProduct: String="",
    val price: Int=0,
    val location:GeoPoint?=GeoPoint(0.0 , 0.0),
    var document:String=""
)
data class UserProfile(
    val userName:String = "",
    val userEmail:String = "",
    val userBirth:String = "",
    var imageView:String = ""
)