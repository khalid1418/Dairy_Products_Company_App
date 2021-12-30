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
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.dairyproductscompanyapp.databinding.FragmentAddBinding
import com.example.dairyproductscompanyapp.utils.ViewModelFactory
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.emptyFlow
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
  private lateinit var  fileImage:Uri



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
        binding?.SaveProduct?.setOnClickListener {
            addNewProduct()
        }

        binding?.imageCompany?.setOnClickListener {
            openGalleryForImage()

        }
    }
    private fun addNewProduct(){
        if (isEntryValid()){
        viewModel.addNewProduct(
            binding?.editNameCompany?.text.toString(),
            binding?.editPhoneNumber1?.text.toString() ,
            binding?.editNameProduct?.text.toString(),
            binding?.price?.text.toString(),
            binding?.imageCompany.toString()


        )
            upLoadImage()
            val action = AddFragmentDirections.actionAddFragmentToListFragment()
            findNavController().navigate(action)
        }
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
    fun upLoadImage() {


        val firestore = UUID.randomUUID().toString()
        val storageRef = FirebaseStorage.getInstance().getReference("/images/$firestore")
                storageRef.putFile(fileImage).addOnSuccessListener {
                    Log.e("TAG", "before:${it}")
                    storageRef.downloadUrl.addOnSuccessListener {
                        Log.e("TAG", "imageFile:${it}")
                    }

                }


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