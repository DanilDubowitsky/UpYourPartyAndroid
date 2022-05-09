package com.example.upyourpartyandroid.ui.fragments.my_advertisements.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.enteties.advertisement.DomainFullAdvertisement

class MyAdvertisementDiffUtil : DiffUtil.ItemCallback<DomainFullAdvertisement>() {

    override fun areItemsTheSame(
        oldItem: DomainFullAdvertisement,
        newItem: DomainFullAdvertisement
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: DomainFullAdvertisement,
        newItem: DomainFullAdvertisement
    ): Boolean =
        oldItem.price == newItem.price &&
        oldItem.description == newItem.description &&
        oldItem.title == newItem.title &&
        oldItem.category == newItem.category

}