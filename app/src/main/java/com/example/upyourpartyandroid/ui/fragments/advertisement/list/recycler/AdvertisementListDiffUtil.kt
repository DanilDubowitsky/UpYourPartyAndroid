package com.example.upyourpartyandroid.ui.fragments.advertisement.list.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.advertisement.DomainAdvertisement

class AdvertisementListDiffUtil : DiffUtil.ItemCallback<DomainAdvertisement>() {

    override fun areItemsTheSame(
        oldItem: DomainAdvertisement,
        newItem: DomainAdvertisement
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: DomainAdvertisement,
        newItem: DomainAdvertisement
    ): Boolean =
        oldItem.isFavorite == newItem.isFavorite
        && oldItem.category == newItem.category
        && oldItem.city == newItem.city
        && oldItem.title == newItem.title
        && oldItem.images == newItem.images
        && oldItem.price == newItem.price

}