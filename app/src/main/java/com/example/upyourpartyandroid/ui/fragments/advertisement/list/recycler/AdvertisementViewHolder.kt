package com.example.upyourpartyandroid.ui.fragments.advertisement.list.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.upyourpartyandroid.databinding.ViewHolderAdvertisementBinding
import com.example.upyourpartyandroid.ui.fragments.advertisement.info.pager.ImagesPagerAdapter

class AdvertisementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ViewHolderAdvertisementBinding.bind(itemView)
    val adapter = ImagesPagerAdapter()
}
