package com.example.dairyproductscompanyapp.orderProduct

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dairyproductscompanyapp.R
import com.example.dairyproductscompanyapp.databinding.FragmentOrderProductCompanyBinding
import com.example.dairyproductscompanyapp.detailCompanyUi.DetailCompanyFragmentArgs
import com.example.dairyproductscompanyapp.detailCompanyUi.DetailCompanyFragmentDirections
import com.example.dairyproductscompanyapp.utils.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class OrderProductCompanyFragment : Fragment() {
    private var _binding:FragmentOrderProductCompanyBinding? =null
     private val binding get() = _binding
    private val viewModel:OrderProductCompanyViewModel by activityViewModels {
        ViewModelFactory()
    }
    private val navigationArgs2:OrderProductCompanyFragmentArgs by navArgs()
    private val userid = Firebase.auth.currentUser?.uid

fun addOrder(){
    viewModel.addNewOrder(
        binding?.quantity?.text.toString(),
        userid.toString(),
        navigationArgs2.id

    )
    val action = OrderProductCompanyFragmentDirections.actionOrderProductCompanyFragmentToListFragment()
    findNavController().navigate(action)

}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderProductCompanyBinding.inflate(inflater , container , false)
        return binding?.root
    }
    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.titleDialog))
                .setMessage(getString(R.string.dialogMassage))
                .setCancelable(false)
                .setNegativeButton(R.string.canceldialog) { _, _ ->  }
                    .setPositiveButton(R.string.sendDialog) { _, _ ->
                        addOrder()
                        Toast.makeText(context, "send is finish", Toast.LENGTH_SHORT).show()

    }
    .show()
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.nameCompany?.text = navigationArgs2.namecompany
        binding?.nameProduct?.text = navigationArgs2.nameproduct
        binding?.price?.text=navigationArgs2.price.toString()
        binding?.quantity?.text=navigationArgs2.quantity.toString()


        binding?.send?.setOnClickListener {
         showConfirmationDialog()
        }

    }
}