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

class MyAdvertisementsFragment : BaseRequestFragment<FragmentMyAdvertisementsBinding, MyAdvertisementsViewModel>(
    MyAdvertisementsViewModel::class,
    FragmentMyAdvertisementsBinding::inflate
) {

    private lateinit var adapter: MyAdvertisementAdapter

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
        adapter = MyAdvertisementAdapter()
        binding.myAdvertisementsRecycler.adapter = adapter
    }

    private fun render(state: MyAdvertisementsState) {
        adapter.submitList(state.advertisements)
    }

    private fun handleSideEffect(sideEffects: BaseSideEffects) {
        when(sideEffects) {

            is BaseSideEffects.ShowMessage -> showSnackBar(sideEffects.message)

        }
    }

    private fun setupListeners() = with(binding) {
        addAdvertisementButton.setClickListener {
            viewModel.onAddAdvertisementClick()
        }
    }


}