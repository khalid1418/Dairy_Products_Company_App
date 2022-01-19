package com.example.dairyproductscompanyapp.model

import com.google.firebase.firestore.GeoPoint
import com.google.firebase.ktx.Firebase
import java.lang.ref.Reference
import java.text.NumberFormat


data class CompanyDataModel(
    val nameCompany:String ="",
    val phone:Int =0,
    var image: String = "",
    val nameProduct:String = "",
    val price:Double = 0.0,
    val userid:String="",
    var reference:String =""

)

data class OrderDataModel(
    val quantity:Int =0 ,
    val buyerId:String ="",
    val sellerId:String="",
    val reference:String="",
    val nameProduct: String="",
    val price: String ="",
    val location:GeoPoint?=GeoPoint(0.0 , 0.0),
    var document:String=""
)
data class UserProfile(
    val userName:String = "",
    val userEmail:String = "",
    val userBirth:String = "",
    var imageView:String = ""
)