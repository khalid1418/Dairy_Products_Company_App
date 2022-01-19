package com.example.dairyproductscompanyapp.listCompanyUi

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dairyproductscompanyapp.R
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.databinding.CompanyListBinding
import com.example.dairyproductscompanyapp.databinding.FragmentAddBinding
import kotlinx.coroutines.withContext

class CompanyListAdapter(private val onItemClicked: (CompanyDataModel) -> Unit) :
    ListAdapter<CompanyDataModel, CompanyListAdapter.CompanyViewHolder>(DiffCallback) {


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<CompanyDataModel>() {
            override fun areItemsTheSame(
                oldItem: CompanyDataModel,
                newItem: CompanyDataModel
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: CompanyDataModel,
                newItem: CompanyDataModel
            ): Boolean {
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
                Glide.with(photoCompany)
                    .load(dataModel.image)
                    .placeholder(R.drawable.loading_animation)
                    .into(photoCompany)


            }

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompanyViewHolder {
        return CompanyViewHolder(
            CompanyListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }

        holder.bind(current)

    }
}