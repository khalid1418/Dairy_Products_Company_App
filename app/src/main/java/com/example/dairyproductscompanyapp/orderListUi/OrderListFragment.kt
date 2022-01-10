package com.example.dairyproductscompanyapp.orderListUi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.dairyproductscompanyapp.databinding.FragmentOrderListBinding
import com.example.dairyproductscompanyapp.utils.ViewModelFactory

class OrderListFragment : Fragment() {
private var _binding:FragmentOrderListBinding?=null
    private val binding get() = _binding
    private val viewModel:OrderListViewModel by activityViewModels {
        ViewModelFactory()
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderListBinding.inflate(inflater , container , false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = OrderAdapter()
        binding?.recyclerView?.adapter=adapter



        viewModel.orderLiveData.observe(viewLifecycleOwner , {
            it.let {
                adapter.submitList(it)
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}