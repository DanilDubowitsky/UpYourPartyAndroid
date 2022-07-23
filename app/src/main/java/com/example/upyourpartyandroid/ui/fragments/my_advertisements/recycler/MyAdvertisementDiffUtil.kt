package com.example.upyourpartyandroid.ui.fragments.my_advertisements.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entities.advertisement.DomainAdvertisement

class MyAdvertisementDiffUtil : DiffUtil.ItemCallback<DomainAdvertisement>() {

    override fun areItemsTheSame(
        oldItem: DomainAdvertisement,
        newItem: DomainAdvertisement
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: DomainAdvertisement,
        newItem: DomainAdvertisement
    ): Boolean =
        oldItem.price == newItem.price &&
        oldItem.description == newItem.description &&
        oldItem.title == newItem.title &&
        oldItem.category == newItem.category &&
        oldItem.images == newItem.images

}