package com.example.upyourpartyandroid.ui.fragments.my_advertisements.create

import android.os.Bundle
import android.view.View
import com.example.upyourpartyandroid.databinding.FragmentCreatingAdvertisementBinding
import com.example.upyourpartyandroid.ui.fragments.base.BaseRequestFragment
import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects

class CreatingAdvertisementsFragment :
    BaseRequestFragment<FragmentCreatingAdvertisementBinding, CreatingAdvertisementsViewModel>(
        CreatingAdvertisementsViewModel::class,
        FragmentCreatingAdvertisementBinding::inflate
    ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observe(this, ::render, ::handleSideEffect)
    }

    private fun render(state: CreatingAdvertisementsState) {

    }

    private fun handleSideEffect(sideEffects: BaseSideEffects) {

    }

    private fun setupListeners() {

    }

}