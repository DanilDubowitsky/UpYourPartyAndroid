package com.example.upyourpartyandroid.ui.fragments.advertisement.list.recycler

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.domain.model.advertisement.DomainAdvertisement
import com.example.domain.model.advertisement.DomainAdvertisementCategory
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.ui.views.ViewUtils.inflate
import com.example.upyourpartyandroid.ui.views.ViewUtils.setClickListener
import javax.inject.Inject

class AdvertisementListRecyclerAdapter @Inject constructor():
    ListAdapter<DomainAdvertisement, AdvertisementViewHolder>(
        AdvertisementListDiffUtil()
    ) {

    private lateinit var context: Context

    private var onFavoriteClick: ((position: Int) -> Unit)? = null

    private var onHolderClick: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisementViewHolder {
        val view = parent.inflate(R.layout.view_holder_advertisement)
        context = parent.context
        return AdvertisementViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdvertisementViewHolder, position: Int) {
        val item = currentList[position]
        with(holder.binding) {
            title.text = item.title
            price.text = item.price.toString()
            city.text = item.city
            Glide.with(holder.itemView)
                .load(item.images.first())
                .centerCrop()
                .into(holder.binding.image)
            if (item.isFavorite) favoriteBtn.setImageResource(R.drawable.ic_filled_favorite)
            else favoriteBtn.setImageResource(R.drawable.ic_outlined_favorite)

            holder.itemView.setClickListener {
                onHolderClick?.invoke(position)
            }

            holder.binding.favoriteBtn.setClickListener {
                onFavoriteClick?.invoke(position)
            }

            category.text = when (item.category) {
                DomainAdvertisementCategory.WEDDING -> context.getString(R.string.advertisements_category_wedding)
                DomainAdvertisementCategory.CORPORATE -> context.getString(R.string.advertisements_category_corporate)
                DomainAdvertisementCategory.BIRTHDAY -> context.getString(R.string.advertisements_category_birthday)
                DomainAdvertisementCategory.PARTY -> context.getString(R.string.advertisements_category_party)
            }
        }
    }

    fun setOnHolderClickListener(listener: (position: Int) -> Unit) {
        this.onHolderClick = listener
    }

    fun setOnFavoriteClickListener(listener: (position: Int) -> Unit) {
        this.onFavoriteClick = listener
    }

}