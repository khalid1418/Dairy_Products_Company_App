package com.example.dairyproductscompanyapp.editProfileUi

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
import com.example.dairyproductscompanyapp.databinding.FragmentEditProductBinding
import com.example.dairyproductscompanyapp.databinding.FragmentEditProfileBinding
import com.example.dairyproductscompanyapp.utils.ViewModelFactory


class EditProfileFragment : Fragment() {
    private var _binding:FragmentEditProfileBinding?=null
    private val binding get() = _binding
    private val viewModel:EditProfileViewModel by activityViewModels{
        ViewModelFactory()
    }
    private val navigationArgs4:EditProfileFragmentArgs by navArgs()
    private var fileImage: Uri? =null
    private val REQUEST_CODE = 100

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            binding?.profileImage?.setImageURI(data?.data) // handle chosen image
            fileImage = data?.data!!
            Log.e("TAG", "fileimag = ${fileImage}")
        }
    }



    private fun bind(){
        binding?.apply {
            editNameProfile.setText(navigationArgs4.username , TextView.BufferType.SPANNABLE)
            editEmail.setText(navigationArgs4.email , TextView.BufferType.SPANNABLE)
            editBirthDate.setText(navigationArgs4.date , TextView.BufferType.SPANNABLE)
            Glide.with(profileImage).load(navigationArgs4.image).error(R.drawable.ic_baseline_account_circle_24).into(profileImage)

        }

    }

    fun addNewProfile(){

            viewModel.setNewProfile(
                binding?.editNameProfile?.text.toString(),
                binding?.editEmail?.text.toString(),
                binding?.editBirthDate?.text.toString(),
                navigationArgs4.image , fileImage



                )}
//    private fun isEntryValid(): Boolean {
//        return viewModel.isEntryValid(
//            binding?.editNameProfile?.text.toString(),
//            binding?.editEmail?.text.toString(),
//            binding?.editBirthDate?.text.toString(),
//        )
//    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentEditProfileBinding.inflate(inflater , container , false)
        return binding?.root
            }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        binding?.profileImage?.setOnClickListener {
            openGalleryForImage()
        }

        binding?.saveEdit?.setOnClickListener {
            addNewProfile()
            val action =EditProfileFragmentDirections.actionEditProfileFragmentToProfileFragment()
            findNavController().navigate(action)
        }
    }

}