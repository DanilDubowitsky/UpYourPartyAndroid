package com.example.upyourpartyandroid.ui.fragments.my_advertisements.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.enteties.advertisement.DomainFullAdvertisement
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.ui.views.ViewUtils.inflate
import javax.inject.Inject

class MyAdvertisementAdapter @Inject constructor() : ListAdapter<DomainFullAdvertisement, MyAdvertisementViewHolder>(MyAdvertisementDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdvertisementViewHolder {
        val itemView = parent.inflate(R.layout.view_holder_my_advertisements)
        return MyAdvertisementViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdvertisementViewHolder, position: Int) {
        val item = currentList[position]

        holder.binding.category.text = item.category
        holder.binding.price.text = item.price.toString()
        holder.binding.title.text = item.title
    }


}