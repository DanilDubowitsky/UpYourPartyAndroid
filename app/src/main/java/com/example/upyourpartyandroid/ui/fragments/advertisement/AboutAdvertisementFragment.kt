package com.example.upyourpartyandroid.ui.fragments.advertisement

import android.os.Bundle
import android.view.View
import com.example.domain.entities.advertisement.DomainAdvertisementCategory
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.databinding.FragmentAdvertisementInfoBinding
import com.example.upyourpartyandroid.ui.Utils.argumentsLong
import com.example.upyourpartyandroid.ui.fragments.advertisement.pager.ImagesPagerAdapter
import com.example.upyourpartyandroid.ui.fragments.base.BaseRequestFragment
import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupViews()
        viewModel.observe(this, ::render, ::onSideEffect)
        viewModel.loadData(advertisementId)
    }

    private fun setupViews() {
        binding.photosPager.adapter = adapter
    }

    private fun render(state: AboutAdvertisementState) = with(binding) {
        state.advertisement?.let { advertisement ->
            adapter.setNewData(advertisement.images)
            titleText.text = advertisement.title
            descriptionText.text = advertisement.description
            priceText.text = advertisement.price.toString()
            categoryText.text = when (advertisement.category) {
                DomainAdvertisementCategory.BIRTHDAY -> getString(R.string.advertisements_category_birthday)
                DomainAdvertisementCategory.CORPORATE -> getString(R.string.advertisements_category_corporate)
                DomainAdvertisementCategory.PARTY -> getString(R.string.advertisements_category_party)
                DomainAdvertisementCategory.WEDDING -> getString(R.string.advertisements_category_wedding)
            }
            if (advertisement.isMy) sendMessageBtn.tryChangeVisibility(View.GONE)
            else sendMessageBtn.tryChangeVisibility(View.VISIBLE)
            phoneText.text = advertisement.phoneNumber
        }
    }

    private fun onSideEffect(sideEffects: BaseSideEffects) {
        when (sideEffects) {
            is BaseSideEffects.ShowLoadingIndicator -> showLoadingIndicator()
            is BaseSideEffects.HideLoadingIndicator -> hideLoadingIndicator()
            is BaseSideEffects.ShowMessage -> showSnackBar(sideEffects.message)
        }
    }

}
