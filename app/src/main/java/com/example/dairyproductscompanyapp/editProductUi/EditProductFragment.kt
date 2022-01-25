package com.example.dairyproductscompanyapp.editProductUi

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.dairyproductscompanyapp.R
import com.example.dairyproductscompanyapp.addCompanyUi.AddFragmentDirections
import com.example.dairyproductscompanyapp.databinding.FragmentEditProductBinding
import com.example.dairyproductscompanyapp.detailCompanyUi.DetailCompanyFragmentArgs
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.utils.ViewModelFactory
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class EditProductFragment() : Fragment() {
    private var _binding: FragmentEditProductBinding? = null
    private val binding get() = _binding
    private val viewModel: EditProductViewModel by activityViewModels {
        ViewModelFactory()
    }
    val userId = Firebase.auth.currentUser?.uid
    private val navigationArgs3: EditProductFragmentArgs by navArgs()
    private val REQUEST_CODE = 100
    private var fileImage: Uri? = null


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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProductBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        binding?.imageCompany?.setOnClickListener {
            openGalleryForImage()

        }
        binding?.SaveProduct?.setOnClickListener {
            editProduct()
        }
    }

    private fun bind() {
        val price = navigationArgs3.price
        binding?.apply {
            editNameCompany.setText(navigationArgs3.namecompany, TextView.BufferType.SPANNABLE)
            editPhoneNumber1.setText(
                navigationArgs3.phone.toString(),
                TextView.BufferType.SPANNABLE
            )
            editNameProduct.setText(navigationArgs3.nameproduct, TextView.BufferType.SPANNABLE)
            priceEdit.setText(price, TextView.BufferType.SPANNABLE)
            Glide.with(imageCompany).load(navigationArgs3.image).into(imageCompany)

        }

    }


    private fun editProduct() {

        if (isEntryValid()) {

            viewModel.setNewProduct(
                binding?.editNameCompany?.text.toString(),
                binding?.editPhoneNumber1?.text.toString(),
                binding?.editNameProduct?.text.toString(),
                binding?.priceEdit?.text.toString(),
                navigationArgs3.image, userId!!, navigationArgs3.refrence, fileImage


            )
            val action = EditProductFragmentDirections.actionEditProductFragmentToListFragment()
            findNavController().navigate(action)
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding?.editNameCompany?.text.toString(),
            binding?.editPhoneNumber1?.text.toString(),
            binding?.editNameProduct?.text.toString(),
            binding?.priceEdit?.text.toString(),
            binding?.imageCompany.toString()
        )
    }

}