package com.example.upyourpartyandroid.ui.fragments.review

import android.os.Bundle
import android.view.View
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.databinding.FragmentSubmitReviewBinding
import com.example.upyourpartyandroid.ui.Utils.argumentsLong
import com.example.upyourpartyandroid.ui.fragments.base.BaseRequestFragment
import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects
import com.example.upyourpartyandroid.ui.views.ViewUtils.setClickListener

class SubmitReviewFragment : BaseRequestFragment<FragmentSubmitReviewBinding, SubmitReviewViewModel>(
    SubmitReviewViewModel::class,
    FragmentSubmitReviewBinding::inflate
) {

    var advertisementId by argumentsLong()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupListeners()
        viewModel.prepare(advertisementId)
        viewModel.observe(this, ::render, ::onSideEffect)
    }

    private fun render(state: SubmitReviewState) = with(binding) {
        when (state.selectedRating) {
            1f,
            0f -> ratingDescription.setText(R.string.submit_review_rating_0)
            2f -> ratingDescription.setText(R.string.submit_review_rating_2)
            3f -> ratingDescription.setText(R.string.submit_review_rating_3)
            4f -> ratingDescription.setText(R.string.submit_review_rating_4)
            5f -> ratingDescription.setText(R.string.submit_review_rating_5)
        }
    }

    private fun onSideEffect(sideEffects: BaseSideEffects) {
        when (sideEffects) {
            is BaseSideEffects.ShowLoadingIndicator -> showLoadingIndicator()
            is BaseSideEffects.HideLoadingIndicator -> hideLoadingIndicator()
        }
    }

    private fun setupListeners() = with(binding) {
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.onRatingChange(rating)
        }
        submit.setClickListener(::onSubmitClick)
        btnBack.setClickListener(viewModel::onBackClick)
    }

    private fun onSubmitClick() = with(binding) {
        val content = comment.text.toString().trim()
        viewModel.submitReview(ratingBar.rating, content)
    }

}