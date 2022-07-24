package com.example.upyourpartyandroid.ui.fragments.advertisement.list.recycler

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.entities.advertisement.DomainAdvertisement
import com.example.domain.entities.advertisement.DomainAdvertisementCategory
import com.example.domain.entities.advertisement.DomainFullAdvertisement
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.ui.views.ViewUtils.inflate
import javax.inject.Inject

class AdvertisementListRecyclerAdapter @Inject constructor():
    ListAdapter<DomainAdvertisement, AdvertisementViewHolder>(
        AdvertisementListDiffUtil()
    ) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisementViewHolder {
        val view = parent.inflate(R.layout.view_holder_advertisement)
        context = parent.context
        return AdvertisementViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdvertisementViewHolder, position: Int) {
        val item = currentList[position]
        holder.binding.imagesPager.adapter = holder.adapter
        holder.adapter.setNewData(item.images)
        with(holder.binding) {
            title.text = item.title
            price.text = item.price.toString()
            city.text = item.city
            if (item.isFavorite) favoriteBtn.setImageResource(R.drawable.ic_filled_favorite)
            else favoriteBtn.setImageResource(R.drawable.ic_outlined_favorite)
            category.text = when (item.category) {
                DomainAdvertisementCategory.WEDDING -> context.getString(R.string.advertisements_category_wedding)
                DomainAdvertisementCategory.CORPORATE -> context.getString(R.string.advertisements_category_corporate)
                DomainAdvertisementCategory.BIRTHDAY -> context.getString(R.string.advertisements_category_birthday)
                DomainAdvertisementCategory.PARTY -> context.getString(R.string.advertisements_category_party)
            }
        }
    }


}