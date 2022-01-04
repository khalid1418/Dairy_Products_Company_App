package com.example.dairyproductscompanyapp.addCompanyUi

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.dairyproductscompanyapp.databinding.FragmentAddBinding
import com.example.dairyproductscompanyapp.utils.ViewModelFactory
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.*


class AddFragment : Fragment() {
    private val viewModel: AddViewModel by activityViewModels {
        ViewModelFactory()
    }
//    var pd = ProgressDialog(context)

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding
    private val REQUEST_CODE = 100
    lateinit var fileImage: Uri
    val userId = Firebase.auth.currentUser?.uid



    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            binding?.imageCompany?.setImageURI(data?.data) // handle chosen image
            fileImage = data?.data!!
            Log.e("TAG", "fileimag = ${fileImage}")
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
        _binding = FragmentAddBinding.inflate(inflater, container, false)
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

    private fun addNewProduct() {

        if (isEntryValid()) {

            viewModel.addNewProduct(
                binding?.editNameCompany?.text.toString(),
                binding?.editPhoneNumber1?.text.toString(),
                binding?.editNameProduct?.text.toString(),
                binding?.price?.text.toString(),
                 "",fileImage ,
                userId!!

            )
            val action = AddFragmentDirections.actionAddFragmentToListFragment()
            findNavController().navigate(action)
        }
    }


    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding?.editNameCompany?.text.toString(),
            binding?.editPhoneNumber1?.text.toString(),
            binding?.editNameProduct?.text.toString(),
            binding?.price?.text.toString(),
            binding?.imageCompany.toString()
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}