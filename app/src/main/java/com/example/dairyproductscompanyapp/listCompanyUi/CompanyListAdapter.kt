package com.example.dairyproductscompanyapp.listCompanyUi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.databinding.CompanyListBinding

class CompanyListAdapter :
    ListAdapter<CompanyDataModel, CompanyListAdapter.CompanyViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<CompanyDataModel>() {
            override fun areItemsTheSame(oldItem: CompanyDataModel, newItem: CompanyDataModel): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: CompanyDataModel, newItem: CompanyDataModel): Boolean {
                return oldItem.nameCompany == newItem.nameCompany
            }

        }
    }

    class CompanyViewHolder(private val binding: CompanyListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataModel: CompanyDataModel) {
            binding.apply {
                name.text = dataModel.nameCompany
                phone.text = dataModel.phone.toString()

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