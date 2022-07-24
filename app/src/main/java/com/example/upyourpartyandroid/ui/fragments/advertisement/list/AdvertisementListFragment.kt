package com.example.upyourpartyandroid.ui.fragments.advertisement.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.converters.toEnumModel
import com.example.domain.entities.advertisement.DomainAdvertisementCategory
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.databinding.FragmentAdvertisementsBinding
import com.example.upyourpartyandroid.ui.Utils.argumentsString
import com.example.upyourpartyandroid.ui.fragments.advertisement.list.recycler.AdvertisementListRecyclerAdapter
import com.example.upyourpartyandroid.ui.fragments.base.BaseRequestFragment
import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects
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
        viewModel.observe(this, ::render, ::onSideEffect)
        viewModel.loadData(category)
    }

    private fun setupViews() = with(binding) {
        val enumCategory = category.toEnumModel<DomainAdvertisementCategory>()
        title.text = when (enumCategory) {
            DomainAdvertisementCategory.CORPORATE -> getString(R.string.advertisements_category_corporate)
            DomainAdvertisementCategory.PARTY -> getString(R.string.advertisements_category_party)
            DomainAdvertisementCategory.BIRTHDAY -> getString(R.string.advertisements_category_birthday)
            DomainAdvertisementCategory.WEDDING -> getString(R.string.advertisements_category_wedding)
        }
    }

    private fun setupRecycler() = with(binding) {
        advertisementsRecycler.adapter = recyclerAdapter
        advertisementsRecycler.layoutManager = GridLayoutManager(requireContext(), RECYCLER_SPAN_COUNT)
    }

    private fun render(state: AdvertisementListState) {
        recyclerAdapter.submitList(state.advertisements)
    }

    private fun onSideEffect(sideEffects: BaseSideEffects) {

    }

    companion object {
        const val RECYCLER_SPAN_COUNT = 2
    }

}
