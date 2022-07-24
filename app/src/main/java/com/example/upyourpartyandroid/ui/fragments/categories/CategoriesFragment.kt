package com.example.upyourpartyandroid.ui.fragments.categories

import android.os.Bundle
import android.view.View
import com.example.upyourpartyandroid.databinding.FragmentCategoriesBinding
import com.example.upyourpartyandroid.ui.fragments.base.BaseFragment
import com.example.upyourpartyandroid.ui.fragments.base.BaseRequestFragment
import com.example.upyourpartyandroid.ui.views.ViewUtils.setClickListener

class CategoriesFragment : BaseRequestFragment<FragmentCategoriesBinding, CategoriesViewModel>(
    CategoriesViewModel::class,
    FragmentCategoriesBinding::inflate
){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupListeners()
    }

    private fun setupListeners() = with(binding) {
        weddingContainer.setClickListener(viewModel::onWeddingClick)
        birthDayContainer.setClickListener(viewModel::onBirthDayClick)
        childPartyContainer.setClickListener(viewModel::onPartyClick)
        corporateContainer.setClickListener(viewModel::onCorporateClick)
    }
}
