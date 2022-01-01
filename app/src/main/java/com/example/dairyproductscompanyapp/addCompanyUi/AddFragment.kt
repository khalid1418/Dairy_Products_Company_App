package com.example.dairyproductscompanyapp.addCompanyUi

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toIcon
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dairyproductscompanyapp.databinding.FragmentAddBinding
import com.example.dairyproductscompanyapp.utils.ViewModelFactory
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import java.net.URL
import java.util.*


class AddFragment : Fragment() {
    private val viewModel:AddViewModel by  activityViewModels {
        ViewModelFactory()
    }

    private val REQUEST_CODE = 100
   private var _binding : FragmentAddBinding? = null
    private val binding get()=_binding
  lateinit var  fileImage:Uri



    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            binding?.imageCompany?.setImageURI(data?.data) // handle chosen image
            fileImage = data?.data!!
            Log.e("TAG","fileimag = ${fileImage}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentAddBinding.inflate(inflater , container ,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.imageCompany?.setOnClickListener {
            openGalleryForImage()

        }

        binding?.SaveProduct?.setOnClickListener {
            addNewProduct()
        }


    }
    private fun addNewProduct(){
        if (isEntryValid()) {
//            upLoadImage()
//            Log.e("TAG","idk:${fileImage}")
//            val firestore = UUID.randomUUID().toString()
//            val storageRef = FirebaseStorage.getInstance().getReference("/images/$firestore")
//            storageRef.putFile(fileImage).addOnSuccessListener {
//                storageRef.downloadUrl.addOnSuccessListener { imageUri ->
//                    Log.e("TAG", "imageUrl:${imageUri}")


                    viewModel.addNewProduct(
                        binding?.editNameCompany?.text.toString(),
                        binding?.editPhoneNumber1?.text.toString(),
                        binding?.editNameProduct?.text.toString(),
                        binding?.price?.text.toString(),
                        fileImage.toString()
                    )
                }
//            }
//        }





            val action = AddFragmentDirections.actionAddFragmentToListFragment()
            findNavController().navigate(action)
        }

    private fun isEntryValid():Boolean{
        return viewModel.isEntryValid(
            binding?.editNameCompany?.text.toString(),
            binding?.editPhoneNumber1?.text.toString(),
            binding?.editNameProduct?.text.toString(),
            binding?.price?.text.toString(),
            binding?.imageCompany.toString()
        )
    }
    fun upLoadImage(): Uri {

            val firestore = UUID.randomUUID().toString()
            val storageRef = FirebaseStorage.getInstance().getReference("/images/$firestore")
            storageRef.putFile(fileImage).addOnCompleteListener {task ->
                Log.e("TAG","result:${task.result}")
               storageRef.downloadUrl.addOnCompleteListener {task->
                   if (task.isSuccessful){
                       fileImage = task.result
                   }


                }
            }


return fileImage
    }
//    fun downLoadImage(){
//        val firestore = UUID.randomUUID().toString()
//
//        val storageRef = FirebaseStorage.getInstance().getReference("/images/$firestore")
//
//        storageRef.downloadUrl.addOnSuccessListener {
//            Log.e("TAG","imageFile:${it}")
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }






//Glide.with.load(image_url.touri)
//
//        storageRef.downloadUrl.addOnSuccessListener {
//            Log.e("TAG","uri:${it}")
//        }








}