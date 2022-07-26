package com.example.upyourpartyandroid.ui.fragments.advertisement.info

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.domain.model.review.Review
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.databinding.ViewHolderReviewBinding
import com.example.upyourpartyandroid.ui.views.ViewUtils.inflate
import javax.inject.Inject

class ReviewsAdapter @Inject constructor() :
    ListAdapter<Review, ReviewViewHolder>(ReviewDiffUtil()) {

    private var onReviewTextClickListener: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = parent.inflate(R.layout.view_holder_review)
        return ReviewViewHolder(ViewHolderReviewBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) = with(holder) {
        val item: Review = getItem(position)
        bindAvatar(item.user.userProfile.avatar)
        bindUserName(item.user.userProfile.name, item.user.userProfile.surname)
        bindDate(item.date)
        bindContent(item.content)
        bindRating(item.rating)
        binding.content.setOnClickListener {
            if (binding.content.maxLines == REVIEW_CONTENT_MIN_LINES)
                binding.content.maxLines = REVIEW_CONTENT_MAX_LINES
            else binding.content.maxLines = REVIEW_CONTENT_MIN_LINES
        }
    }

    fun ReviewViewHolder.bindRating(rating: String) {
        binding.rating.rating = rating.toFloat()
    }

    fun ReviewViewHolder.bindContent(content: String) {
        binding.content.text = content
    }

    fun ReviewViewHolder.bindDate(date: String) {
        binding.date.text = date
    }

    fun ReviewViewHolder.bindUserName(userName: String, surname: String) {
        binding.userName.text = "$userName $surname"
    }

    fun ReviewViewHolder.bindAvatar(avatarUrl: String) {
        if (avatarUrl.isNotBlank())
            Glide.with(itemView)
                .load(avatarUrl)
                .into(binding.avatar)
        else binding.avatar.setImageResource(R.drawable.profileimage)
    }

    fun stOnReviewTextClickListener(listener: (Int) -> Unit) {
        this.onReviewTextClickListener = listener
    }

    companion object {
        const val REVIEW_CONTENT_MIN_LINES = 3
        const val REVIEW_CONTENT_MAX_LINES = 10
    }

}
