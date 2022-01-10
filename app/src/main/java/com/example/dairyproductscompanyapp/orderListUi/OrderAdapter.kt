package com.example.dairyproductscompanyapp.orderListUi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dairyproductscompanyapp.databinding.OrderListBinding
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.model.OrderDataModel

class OrderAdapter:ListAdapter<OrderDataModel , OrderAdapter.OrderViewHolder>(DiffCallback) {
    class OrderViewHolder(private val binding: OrderListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(dataModel: OrderDataModel){
            binding.apply {
                nameProduct.text = dataModel.nameProduct
                price.text=dataModel.price.toString()
                quantity.text=dataModel.quantity.toString()

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



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(OrderListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }
}