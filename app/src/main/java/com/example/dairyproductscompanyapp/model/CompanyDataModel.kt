package com.example.dairyproductscompanyapp.model

import androidx.core.location.LocationRequestCompat
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
    val quantity:Int ,
    val buyerId:String,
    val sellerId:String
)

