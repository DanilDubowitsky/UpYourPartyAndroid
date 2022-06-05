package com.example.upyourpartyandroid.ui.fragments.my_advertisements.recycler

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.domain.enteties.advertisement.DomainAdvertisement
import com.example.domain.enteties.advertisement.DomainAdvertisementCategory
import com.example.domain.enteties.advertisement.DomainFullAdvertisement
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.ui.views.ViewUtils.inflate
import com.example.upyourpartyandroid.ui.views.ViewUtils.setClickListener
import javax.inject.Inject

class MyAdvertisementAdapter @Inject constructor() :
    ListAdapter<DomainAdvertisement, MyAdvertisementViewHolder>(MyAdvertisementDiffUtil()) {

    private lateinit var context: Context

    var onItemClick: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdvertisementViewHolder {
        val itemView = parent.inflate(R.layout.view_holder_my_advertisements)
        context = itemView.context
        return MyAdvertisementViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdvertisementViewHolder, position: Int) {
        val item = currentList[position]

        holder.binding.category.text = when (item.category) {
            DomainAdvertisementCategory.PARTY -> context.getString(R.string.advertisements_category_party)
            DomainAdvertisementCategory.CORPORATE -> context.getString(R.string.advertisements_category_corporate)
            DomainAdvertisementCategory.WEDDING -> context.getString(R.string.advertisements_category_wedding)
            DomainAdvertisementCategory.BIRTHDAY -> context.getString(R.string.advertisements_category_birthday)
        }

        holder.itemView.setClickListener {
            onItemClick?.invoke(position)
        }

        holder.binding.price.text = item.price.toString()
        holder.binding.title.text = item.title
        if(item.images.isEmpty() || item.images.first().isBlank()) {
            Glide.with(context)
                .load(R.drawable.noimage)
                .centerCrop()
                .into(holder.binding.image)
        } else {
            holder.binding.image.setImageResource(R.drawable.noimage)
            Glide.with(context)
                .load(item.images.first())
                .centerCrop()
                .into(holder.binding.image)
        }
    }

}