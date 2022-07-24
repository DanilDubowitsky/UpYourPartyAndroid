package com.example.upyourpartyandroid.ui.fragments.advertisement.info.pager

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.ui.views.ViewUtils.inflate
import javax.inject.Inject

class ImagesPagerAdapter @Inject constructor() : RecyclerView.Adapter<AdvertisementImageViewHolder>() {

    private var data: List<String> = emptyList()

    fun setNewData(newData: List<String>) {
        this.data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdvertisementImageViewHolder {
        val view = parent.inflate(R.layout.view_pager_holder_announce_image)
        return AdvertisementImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdvertisementImageViewHolder, position: Int) {
        val imageUrl = data[position]
        Glide.with(holder.binding.image)
            .load(imageUrl)
            .centerCrop()
            .into(holder.binding.image)
    }

    override fun getItemCount(): Int = data.size
}