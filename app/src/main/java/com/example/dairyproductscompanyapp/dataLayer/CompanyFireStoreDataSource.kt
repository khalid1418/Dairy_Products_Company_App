package com.example.dairyproductscompanyapp.dataLayer

import android.util.Log
import com.example.dairyproductscompanyapp.FakeData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
class CompanyFireStoreDataSource(
    private val firebaseFirestore:FirebaseFirestore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
):product {
     override suspend fun addProduct(product: FakeData){
         val db = firebaseFirestore
         db.collection("product").document("${Firebase.auth.uid}")
             .set(product)
             .addOnSuccessListener {
                     documentReference ->
                 Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference}")
             }
             .addOnFailureListener { e ->
                 Log.w("TAG", "Error adding document", e)
             }
     }





}