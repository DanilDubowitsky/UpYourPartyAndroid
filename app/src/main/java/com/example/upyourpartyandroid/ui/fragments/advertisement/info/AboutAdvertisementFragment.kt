package com.example.upyourpartyandroid.ui.fragments.advertisement.info

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.NumberUtils.RATING_DISPLAY_DECIMALS
import com.example.domain.NumberUtils.formatToDisplay
import com.example.domain.model.advertisement.DomainAdvertisementCategory
import com.example.domain.model.review.Review
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.databinding.FragmentAdvertisementInfoBinding
import com.example.upyourpartyandroid.ui.Utils.argumentsLong
import com.example.upyourpartyandroid.ui.fragments.advertisement.info.pager.ImagesPagerAdapter
import com.example.upyourpartyandroid.ui.fragments.base.BaseRequestFragment
import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects
import com.example.upyourpartyandroid.ui.views.ViewUtils.setClickListener
import com.example.upyourpartyandroid.ui.views.ViewUtils.setOnPageChangeListener
import com.example.upyourpartyandroid.ui.views.ViewUtils.tryChangeVisibility
import javax.inject.Inject

class AboutAdvertisementFragment : BaseRequestFragment<FragmentAdvertisementInfoBinding,
        AboutAdvertisementViewModel>(
    AboutAdvertisementViewModel::class,
    FragmentAdvertisementInfoBinding::inflate
) {

    var advertisementId by argumentsLong()

    @Inject
    lateinit var adapter: ImagesPagerAdapter

    @Inject
    lateinit var reviewsAdapter: ReviewsAdapter

    override fun onResume() {
        super.onResume()
        viewModel.loadData(advertisementId)
        viewModel.loadReviewsData(advertisementId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupViews()
        this.setupListeners()
        viewModel.observe(this, ::render, ::onSideEffect)
    }

    private fun setupViews() = with(binding) {
        photosPager.adapter = adapter
        reviews.adapter = reviewsAdapter
        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(toolBar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupListeners() = with(binding) {
        photosPager.setOnPageChangeListener { position ->
            pagesCount.text = "${position + 1}/${adapter.itemCount}"
        }
        favoriteButton.setClickListener(viewModel::changeFavoriteStatus)
        addReviewBtn.setClickListener(viewModel::onSubmitReviewClick)
    }

    private fun render(state: AboutAdvertisementState) = with(binding) {
        state.advertisement?.let { advertisement ->
            adapter.setNewData(advertisement.images)
            titleText.text = advertisement.title
            descriptionText.text = advertisement.description
            priceText.text = advertisement.price.toString()
            phoneText.text = advertisement.phoneNumber
            advertisement.isFavorite.bindIsFavorite()
            advertisement.isMy.bindIsMy()
            advertisement.rating.bindRating()
            advertisement.category.bindCategory()
        }
        bindReviews(state.reviews)
    }

    private fun bindReviews(reviews: List<Review>) = with(binding) {
        if (reviews.isEmpty()){
            noReviewsText.tryChangeVisibility(View.VISIBLE)
            allReviews.tryChangeVisibility(View.GONE)
        }
        else {
            noReviewsText.tryChangeVisibility(View.GONE)
            allReviews.tryChangeVisibility(View.VISIBLE)
        }
        reviewsAdapter.submitList(reviews)
    }

    private fun Boolean.bindIsFavorite() = with(binding) {
        if (this@bindIsFavorite) favoriteButton.setImageResource(R.drawable.ic_filled_favorite)
        else favoriteButton.setImageResource(R.drawable.ic_outlined_favorite)
    }

    private fun Boolean.bindIsMy() = with(binding) {
        if (this@bindIsMy) sendMessageBtn.tryChangeVisibility(View.GONE)
        else sendMessageBtn.tryChangeVisibility(View.VISIBLE)
    }

    private fun DomainAdvertisementCategory.bindCategory() {
        binding.categoryText.text = when (this) {
            DomainAdvertisementCategory.BIRTHDAY -> getString(R.string.advertisements_category_birthday)
            DomainAdvertisementCategory.CORPORATE -> getString(R.string.advertisements_category_corporate)
            DomainAdvertisementCategory.PARTY -> getString(R.string.advertisements_category_party)
            DomainAdvertisementCategory.WEDDING -> getString(R.string.advertisements_category_wedding)
        }
    }

    private fun Float.bindRating() = with(binding) {
        ratingBar.rating = this@bindRating
        ratingNumber.text = this@bindRating.formatToDisplay(RATING_DISPLAY_DECIMALS)
    }

    private fun onSideEffect(sideEffects: BaseSideEffects) {
        when (sideEffects) {
            is BaseSideEffects.ShowLoadingIndicator -> showLoadingIndicator()
            is BaseSideEffects.HideLoadingIndicator -> hideLoadingIndicator()
            is BaseSideEffects.ShowMessage -> showSnackBar(sideEffects.message)
        }
    }

    override fun onPause() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(null)
        super.onPause()
    }

}
