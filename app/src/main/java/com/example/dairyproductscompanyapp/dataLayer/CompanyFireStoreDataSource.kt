package com.example.dairyproductscompanyapp.dataLayer

import android.app.ProgressDialog
import android.net.Uri
import android.util.Log
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.model.OrderDataModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import java.net.URI
import java.util.*

class CompanyFireStoreDataSource(
    private val firebaseFirestore: FirebaseFirestore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CompanyDataSource {
    override suspend fun addProduct(product: CompanyDataModel, imge: Uri) {
        upload(imge).collect {


            Log.e("TAG","uri :: $it")
            val db = firebaseFirestore
            product.image = it.toString()
        var s = db.collection("product").document()
            var ss = s.id
            product.reference=ss

            Log.e("TAG", "addProduct:${product.reference} " )
                s.set(product)

              .addOnSuccessListener { documentReference ->
                    Log.e("TAG", "DocumentSnapshot added with ID: ${documentReference}")


                    return@addOnSuccessListener
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }

        }

    }



    suspend fun upload(file: Uri): Flow<Uri> = callbackFlow {


        val firestore = UUID.randomUUID().toString()
        val storageRef = FirebaseStorage.getInstance().getReference("/images/$firestore")
        storageRef.putFile(file).addOnSuccessListener {
            storageRef.downloadUrl.addOnSuccessListener { imageUri ->
                Log.e("TAG", "imageUrl:${imageUri}")
                trySend(imageUri)
            }
                .addOnFailureListener {
                    Log.e("TAG","error:$it")
                }

        }

        awaitClose { }

    }

    override suspend fun retrieveCompanyData(): Flow<List<CompanyDataModel>> =
        callbackFlow {

            val db = Firebase.firestore
            val docRef = db.collection("product")
            docRef.addSnapshotListener { snapshot, e ->
                if (e != null) {

                    Log.w("TAG", "Listen failed.", e)

                }
                val list = mutableListOf<CompanyDataModel>()

                for (document in snapshot!!.documents)
                    if (!snapshot.isEmpty) {
                        val productInfo = document.toObject(CompanyDataModel::class.java)
                        list.add(productInfo!!)


                    } else {
                        Log.d("TAG", "Current data: null")
                    }
                trySend(list)
            }
            awaitClose { }
        }

    override suspend fun sendOrderProduct(product: OrderDataModel) {
        val db = firebaseFirestore
        db.collection("order")
            .add(product)
            .addOnCompleteListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.result.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }

    }

    override suspend fun editProduct(product: CompanyDataModel , id:String) {
//        upload(imge).collect {
            val db = firebaseFirestore
//            product.image = it.toString()
        product.reference = id
        Log.e("TAG", "editProduct: hi:$id", )
            db.collection("product").document("${product.reference}")
                .set(product)
                .addOnCompleteListener { documentReference ->
                    Log.e("TAG", "editProduct:${documentReference.result}")
                }
                .addOnFailureListener {

                }
        }
    }



//    override suspend fun getCompanyDetails(product: CompanyDataModel, id: String) {
//
//        val db = Firebase.firestore
//        val docRef = db.collection("product").document()
//        docRef.get()
//    }





//        db.collection("product")
//            .get()
//            .addOnSuccessListener {result ->
//                val retrieveAllInfo = result.toObjects(CompanyDataModel::class.java)
//                Log.e("TAG","massage:${this}")
//                trySend(retrieveAllInfo)
//                for (document in result){
//
//
//                    Log.d("TAG","${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w("TAG","Error getting documents.",exception)
//
//            }