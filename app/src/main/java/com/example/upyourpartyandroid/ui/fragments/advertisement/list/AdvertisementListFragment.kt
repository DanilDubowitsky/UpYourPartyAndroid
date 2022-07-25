package com.example.upyourpartyandroid.ui.fragments.advertisement.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.converters.toEnumModel
import com.example.domain.model.advertisement.DomainAdvertisementCategory
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.databinding.FragmentAdvertisementsBinding
import com.example.upyourpartyandroid.ui.Utils.argumentsString
import com.example.upyourpartyandroid.ui.fragments.advertisement.list.recycler.AdvertisementListRecyclerAdapter
import com.example.upyourpartyandroid.ui.fragments.base.BaseRequestFragment
import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects
import com.example.upyourpartyandroid.ui.views.ViewUtils.setClickListener
import com.example.upyourpartyandroid.ui.views.ViewUtils.tryChangeVisibility
import javax.inject.Inject

class AdvertisementListFragment : BaseRequestFragment<FragmentAdvertisementsBinding, AdvertisementListViewModel>(
    AdvertisementListViewModel::class,
    FragmentAdvertisementsBinding::inflate
) {

    var category: String by argumentsString()

    @Inject
    lateinit var recyclerAdapter: AdvertisementListRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupRecycler()
        this.setupViews()
        this.setupListeners()
        viewModel.observe(this, ::render, ::onSideEffect)
        viewModel.loadData(category)
    }

    private fun setupViews() = with(binding) {
        val enumCategory = category.toEnumModel<DomainAdvertisementCategory>()
        title.text = when (enumCategory) {
            DomainAdvertisementCategory.CORPORATE -> getString(R.string.advertisements_category_corporate).toUpperCase()
            DomainAdvertisementCategory.PARTY -> getString(R.string.advertisements_category_party).toUpperCase()
            DomainAdvertisementCategory.BIRTHDAY -> getString(R.string.advertisements_category_birthday).toUpperCase()
            DomainAdvertisementCategory.WEDDING -> getString(R.string.advertisements_category_wedding).toUpperCase()
        }
    }

    private fun setupListeners() = with(binding) {
        btnBack.setClickListener(viewModel::onBackClick)
        recyclerAdapter.setOnFavoriteClickListener(viewModel::onFavoriteClick)
        recyclerAdapter.setOnHolderClickListener(viewModel::onHolderClick)
    }

    private fun setupRecycler() = with(binding) {
        advertisementsRecycler.adapter = recyclerAdapter
        advertisementsRecycler.layoutManager = GridLayoutManager(requireContext(), RECYCLER_SPAN_COUNT)
    }

    private fun render(state: AdvertisementListState) = with(binding) {
        if (state.advertisements.isEmpty()) {
            noElements.tryChangeVisibility(View.VISIBLE)
        } else {
            noElements.tryChangeVisibility(View.GONE)
        }
        recyclerAdapter.submitList(state.advertisements)
    }

    private fun onSideEffect(sideEffects: BaseSideEffects) {

    }

    companion object {
        const val RECYCLER_SPAN_COUNT = 2
    }

}
