package com.example.dairyproductscompanyapp.addCompanyUi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.dairyproductscompanyapp.FakeData
import com.example.dairyproductscompanyapp.databinding.FragmentAddBinding
import com.example.dairyproductscompanyapp.utils.ViewModelFactory


class AddFragment : Fragment() {
    private val viewModel:AddViewModel by  activityViewModels {
        ViewModelFactory()
    }

    private val REQUEST_CODE = 100
   private var _binding : FragmentAddBinding? = null
    private val binding get()=_binding



    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            binding?.imageCompany?.setImageURI(data?.data) // handle chosen image
            Log.e("TAG","image:${data}")
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
        viewModel.addNewProduct(
            binding?.editNameCompany?.text.toString(),
            binding?.editPhoneNumber1?.text.toString() ,
            binding?.imageCompany?.imageMatrix.toString(),
            binding?.editNameProduct?.text.toString(),
            binding?.price?.text.toString()


        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }






}