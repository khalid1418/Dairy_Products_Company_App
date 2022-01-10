package com.example.dairyproductscompanyapp.orderListUi

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dairyproductscompanyapp.databinding.OrderListBinding
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.model.OrderDataModel
import com.example.dairyproductscompanyapp.utils.ViewModelFactory

class OrderAdapter(private val viewModel: OrderListViewModel) :
    ListAdapter<OrderDataModel, OrderAdapter.OrderViewHolder>(DiffCallback) {


    class OrderViewHolder(private val binding: OrderListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private fun toggleStrikeThrough(
            nameProduct: TextView,
            price: TextView,
            quantity: TextView,
            isChecked: Boolean
        ) {
            if (isChecked) {
                nameProduct.paintFlags = nameProduct.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                price.paintFlags = price.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                quantity.paintFlags = quantity.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            } else {
                nameProduct.paintFlags =
                    nameProduct.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                price.paintFlags = price.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                quantity.paintFlags = quantity.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }

        fun bind(dataModel: OrderDataModel, viewModel: OrderListViewModel) {
            binding.apply {
                nameProduct.text = dataModel.nameProduct
                price.text = dataModel.price.toString()
                quantity.text = dataModel.quantity.toString()
                checkBox4.setOnCheckedChangeListener { _, isChecked ->
                    binding.deleteButton.setOnClickListener {
                        viewModel.deleteOrderDone(dataModel)
                    }
                    toggleStrikeThrough(nameProduct, price, quantity, isChecked)
                }


            }

        }

    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<OrderDataModel>() {
            override fun areItemsTheSame(
                oldItem: OrderDataModel,
                newItem: OrderDataModel
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: OrderDataModel,
                newItem: OrderDataModel
            ): Boolean {
                return oldItem.reference == newItem.reference
            }

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderAdapter.OrderViewHolder {
        return OrderAdapter.OrderViewHolder(OrderListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, viewModel)
    }
}