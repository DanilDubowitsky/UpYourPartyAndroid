package com.example.upyourpartyandroid.ui.fragments.advertisement.info

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.review.Review

class ReviewDiffUtil : DiffUtil.ItemCallback<Review>() {

    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
        oldItem.content == newItem.content &&
        oldItem.date == newItem.date &&
        oldItem.rating == newItem.rating &&
        oldItem.user == newItem.user

}