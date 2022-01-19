package com.example.dairyproductscompanyapp.editProfileUi

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.dairyproductscompanyapp.R
import com.example.dairyproductscompanyapp.databinding.FragmentEditProductBinding
import com.example.dairyproductscompanyapp.databinding.FragmentEditProfileBinding
import com.example.dairyproductscompanyapp.utils.ViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class EditProfileFragment : Fragment() {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding
    private val viewModel: EditProfileViewModel by activityViewModels {
        ViewModelFactory()
    }


    private val navigationArgs4: EditProfileFragmentArgs by navArgs()
    private var fileImage: Uri? = null
    private val REQUEST_CODE = 100

//    private fun datepicker(){
//        val calendarView = java.util.Calendar.getInstance()
//        val year = calendarView.get(Calendar.YEAR)
//        val month = calendarView.get(Calendar.MONTH)
//        val day = calendarView.get(Calendar.DAY_OF_MONTH)
//
//        binding?.datePicker?.setOnClickListener {
//            val dpd = DatePickerDialog(requireContext(), DatePicker.OnDateChangedListener())
//        }
//    }
    private fun convertMillisecondsToReadableDate(
        dateMilliseconds: Long,
        datePattern: String
    ): String {
        val format = SimpleDateFormat(datePattern, Locale.getDefault())
        return format.format(Date(dateMilliseconds))
    }
    fun showDatePicker() {
        var datepick = ""

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        datePicker.show(parentFragmentManager, "DatePicker")
        datePicker.addOnPositiveButtonClickListener {
            datepick = convertMillisecondsToReadableDate(it , "YYY, MM d ")
            binding?.editBirthDate?.setText(datepick)

        }

    }

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


    private fun bind() {
        binding?.apply {
            editNameProfile.setText(navigationArgs4.username, TextView.BufferType.SPANNABLE)
            editEmail.setText(navigationArgs4.email, TextView.BufferType.SPANNABLE)
            editBirthDate.setText(navigationArgs4.date, TextView.BufferType.SPANNABLE)
            if (navigationArgs4.image.isNotBlank()){
                Glide.with(profileImage).load(navigationArgs4.image)
                    .into(profileImage)
            }


        }

    }

    fun addNewProfile() {

        viewModel.setNewProfile(
            binding?.editNameProfile?.text.toString(),
            binding?.editEmail?.text.toString(),
            binding?.editBirthDate?.text.toString(),
            navigationArgs4.image, fileImage


        )
    }

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
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("TAG", "onViewCreated: ${navigationArgs4.image}", )

        binding?.datePicker?.setOnClickListener {
            showDatePicker()
        }

        bind()
        binding?.profileImage?.setOnClickListener {
            openGalleryForImage()
        }

        binding?.saveEdit?.setOnClickListener {
            addNewProfile()
            val action = EditProfileFragmentDirections.actionEditProfileFragmentToProfileFragment()
            findNavController().navigate(action)
        }
    }

}