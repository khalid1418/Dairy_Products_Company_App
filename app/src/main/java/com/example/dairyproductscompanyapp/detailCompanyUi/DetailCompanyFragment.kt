package com.example.dairyproductscompanyapp.detailCompanyUi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.dairyproductscompanyapp.R
import com.example.dairyproductscompanyapp.databinding.FragmentDetailCompanyBinding
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.utils.ViewModelFactory
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class DetailCompanyFragment : Fragment() {
    private var _binding:FragmentDetailCompanyBinding?=null
    private val binding get() = _binding
    private val navigationArgs:DetailCompanyFragmentArgs by navArgs()
    private val viewModel:DetailViewModel by activityViewModels()
    private val userid = Firebase.auth.currentUser?.uid



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCompanyBinding.inflate(inflater , container , false)
        return binding?.root
    }




    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner
        binding.apply {
            binding?.nameCompanyDetail?.text = navigationArgs.namecompany
            binding?.namePhoneDetail?.text = navigationArgs.phonenumber.toString()
            binding?.nameProductDetail?.text = navigationArgs.nameproduct
            binding?.priceDetail?.text = navigationArgs.price.toString()
            Glide.with(this!!.imageCompanyDetail)
                .load(navigationArgs.image)
                .into(this.imageCompanyDetail)
        }

binding?.plusOne?.setOnClickListener {
    viewModel.quantityplus()
}
        binding?.minusOne?.setOnClickListener {
            viewModel.quantitydecrease()
        }
        binding?.orderButton?.setOnClickListener {
            if (viewModel.quantity.value!! > 0) {
                if (userid != null) {
                    val action =
                        DetailCompanyFragmentDirections.actionDetailCompanyFragmentToOrderProductCompanyFragment(
                            viewModel.quantity.value!!.toInt(),
                            navigationArgs.namecompany,
                            navigationArgs.nameproduct,
                            navigationArgs.price,
                            navigationArgs.id,
                            navigationArgs.refrance
                        )
                    findNavController().navigate(action)
                }else{
                    Toast.makeText(context, getString(R.string.toast_sign_in), Toast.LENGTH_SHORT).show()
                }
            }

        }
        if (userid == navigationArgs.id) {
            binding?.editButton?.setOnClickListener {
                val action =
                    DetailCompanyFragmentDirections.actionDetailCompanyFragmentToEditProductFragment(
                        navigationArgs.namecompany,
                        navigationArgs.phonenumber,
                        navigationArgs.image,
                        navigationArgs.nameproduct,
                        navigationArgs.price,
                        navigationArgs.id , navigationArgs.refrance
                    )
                findNavController().navigate(action)
            }
        } else{
            binding?.editButton?.visibility=View.INVISIBLE
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}