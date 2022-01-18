package com.example.dairyproductscompanyapp.dataLayer

import android.net.Uri
import android.util.Log
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.model.OrderDataModel
import com.example.dairyproductscompanyapp.model.UserProfile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import java.util.*
class CompanyFireStoreDataSource(
    private val firebaseFirestore: FirebaseFirestore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CompanyDataSource {

    override suspend fun addProduct(product: CompanyDataModel, imageURI: Uri) {
        upload(imageURI).collect {


            Log.e("TAG", "uri :: $it")
            val db = firebaseFirestore
            product.image = it.toString()
            var path = db.collection("product").document()
            var id = path.id
            product.reference = id

            Log.e("TAG", "addProduct:${product.reference} ")
            path.set(product)

                .addOnSuccessListener { documentReference ->
                    Log.e("TAG", "DocumentSnapshot added with ID: ${documentReference}")


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
                    Log.e("TAG", "error:$it")
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
       val path =  db.collection("order").document()
               var id = path.id
        product.document=id

            path.set(product)
            .addOnCompleteListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.result}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }

    }

    override suspend fun editProduct(product: CompanyDataModel, id: String, image: Uri?) {

        if (image != null) {
            upload(image).collect {
                val db = firebaseFirestore
                product.image = it.toString()
                product.reference = id
                Log.e("TAG", "editProduct: hi:$id")
                db.collection("product").document(product.reference)
                    .set(product)
                    .addOnCompleteListener { documentReference ->
                        Log.e("TAG", "editProduct:${documentReference.result}")
                    }
                    .addOnFailureListener {

                    }
            }
        } else {
            val db = Firebase.firestore
            product.reference = id
            db.collection("product").document(product.reference)
                .set(product)
                .addOnCompleteListener { documentReference ->
                    Log.e("TAG", "editProduct:${documentReference.result}")
                }
        }
    }

    override suspend fun retrieveOrderBuyer(): Flow<List<OrderDataModel>> =
        callbackFlow {
            val db = firebaseFirestore
            val docRef =
                db.collection("order").whereEqualTo("sellerId", "${Firebase.auth.currentUser?.uid}")
            Log.e("TAG","userId:${Firebase.auth.currentUser?.uid}")
            docRef.addSnapshotListener { snap, e ->
                if (e != null) {
                    Log.w("TAG", "Listen failed.", e)
                }
                val list = mutableListOf<OrderDataModel>()

                for (document in snap!!.documents)
                    if (!snap.isEmpty) {
                        val productInfo = document.toObject(OrderDataModel::class.java)
                        list.add(productInfo!!)


                    } else {
                        Log.d("TAG", "Current data: null")

                    }
                    trySend(list)
            }
            awaitClose{ }
        }

    override suspend fun deleteOrderDone(product: OrderDataModel) {
        val db = firebaseFirestore
        val docRef = db.collection("order").document(product.document)
        docRef.delete()
    }

    override suspend fun editUserInfo(profile: UserProfile , image: Uri?) {
        if (image != null) {
            upload(image).collect {
                profile.imageView = it.toString()
                val db = firebaseFirestore
                val docRef = db.collection("User").document(Firebase.auth.currentUser!!.uid)
                docRef.set(profile)
                    .addOnCompleteListener { doc ->
                        Log.e("TAG", "getUserInfowithimage: ${doc.result}",)

                    }
                    .addOnFailureListener {
                        Log.e("TAG", "editUserInfo: bad",)
                    }
            }
        }else{
            val db = firebaseFirestore
            val docRef = db.collection("User").document(Firebase.auth.currentUser!!.uid)
            docRef.set(profile)
                .addOnCompleteListener { doc ->
                    Log.e("TAG", "getUserInfo: ${doc.result}",)

                }
                .addOnFailureListener {
                    Log.e("TAG", "editUserInfo: bad",)
                }
        }
    }

    override suspend fun getInfoUser():Flow<UserProfile> = callbackFlow {
        val db = firebaseFirestore
        val docRef=db.collection("User").document(Firebase.auth.currentUser!!.uid)
            docRef.addSnapshotListener { snap, e ->
                if (e != null){

                }

                if (snap?.data!=null){
                    val productInfo = snap.toObject(UserProfile::class.java)
                    trySend(productInfo!!)
                }

            }


awaitClose {  }



    }

    override suspend fun addProfile(userProfile: UserProfile) {
        val db = firebaseFirestore
        db.collection("User").document("${Firebase.auth.currentUser?.uid}")
            .set(userProfile)
            .addOnSuccessListener {
                Log.d("TAG", "addProfile:$it ")
            }
            .addOnFailureListener {
                Log.e("TAG", "addProfile: $it", )
            }
    }
    companion object{
        const val PROFILE = "PROFILE"
        const val USERNAME = "userName"
        const val USEREMAIL = "userEmail"

    }

}
