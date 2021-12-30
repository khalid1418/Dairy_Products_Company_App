package com.example.dairyproductscompanyapp.dataLayer

import android.util.Log
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class CompanyFireStoreDataSource(
    private val firebaseFirestore:FirebaseFirestore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
):CompanyDataSource {
     override suspend fun addProduct(product: CompanyDataModel){

         val db = firebaseFirestore
         db.collection("product")

             .add(product)
             .addOnCompleteListener {


                     documentReference ->
                 Log.d("TAG", "DocumentSnapshot added with ID: $documentReference")
             }
             .addOnFailureListener { e ->
                 Log.w("TAG", "Error adding document", e)
             }

     }

    override suspend fun retrieveCompany(): Flow<CompanyDataModel> = callbackFlow{
        val db = Firebase.firestore
        db.collection("product")
            .get()
            .addOnSuccessListener {result ->
                for (document in result){
                    Log.d("TAG","${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG","Error getting documents.",exception)

            }


    }






}