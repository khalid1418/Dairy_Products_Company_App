package com.example.dairyproductscompanyapp.listCompanyUi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dairyproductscompanyapp.FakeData
import com.example.dairyproductscompanyapp.databinding.CompanyListBinding

class CompanyListAdapter :
    ListAdapter<FakeData, CompanyListAdapter.CompanyViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<FakeData>() {
            override fun areItemsTheSame(oldItem: FakeData, newItem: FakeData): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: FakeData, newItem: FakeData): Boolean {
                return oldItem.nameCompany == newItem.nameCompany
            }

        }
    }

    class CompanyViewHolder(private val binding: CompanyListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FakeData) {
            binding.apply {
                name.text = data.nameCompany
                phone.text = data.phone.toString()

            }

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompanyViewHolder {
        return CompanyViewHolder(
            CompanyListBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)

    }
}