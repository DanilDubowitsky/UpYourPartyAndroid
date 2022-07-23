package com.example.upyourpartyandroid.ui.fragments.my_advertisements

import android.os.Bundle
import android.view.View
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.databinding.FragmentMyAdvertisementsBinding
import com.example.upyourpartyandroid.ui.fragments.base.BaseRequestFragment
import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.create.CreatingAdvertisementsFragment
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.recycler.MyAdvertisementAdapter
import com.example.upyourpartyandroid.ui.views.ViewUtils.setClickListener
import com.example.upyourpartyandroid.ui.views.ViewUtils.tryChangeVisibility

class MyAdvertisementsFragment : BaseRequestFragment<FragmentMyAdvertisementsBinding, MyAdvertisementsViewModel>(
    MyAdvertisementsViewModel::class,
    FragmentMyAdvertisementsBinding::inflate
) {

    private val adapter: MyAdvertisementAdapter = MyAdvertisementAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupRecycler()
        this.setupListeners()
        viewModel.observe(this, ::render, ::handleSideEffect)
        viewModel.getMyAdvertisements()
    }

    override fun onDestroyView() {
        binding.myAdvertisementsRecycler.adapter = null
        super.onDestroyView()
    }

    private fun setupRecycler() {
        binding.myAdvertisementsRecycler.adapter = adapter
    }

    private fun render(state: MyAdvertisementsState) {
        adapter.submitList(state.advertisements)
    }

    private fun handleSideEffect(sideEffects: BaseSideEffects) = with(binding) {
        when(sideEffects) {
            is BaseSideEffects.ShowLoadingIndicator -> {
                shimmer.startShimmer()
            }
            is BaseSideEffects.HideLoadingIndicator -> {
                shimmer.stopShimmer()
                shimmer.tryChangeVisibility(View.GONE)
                myAdvertisementsRecycler.tryChangeVisibility(View.VISIBLE)
            }
            is BaseSideEffects.ShowMessage -> showSnackBar(sideEffects.message)
        }
    }

    private fun setupListeners() = with(binding) {
        adapter.setOnItemLongClickListener(viewModel::onAdvertisementCLick)
        adapter.setOnItemClickListener(viewModel::onItemClick)
        addAdvertisementButton.setClickListener {
            viewModel.onAddAdvertisementClick()
        }
    }


}