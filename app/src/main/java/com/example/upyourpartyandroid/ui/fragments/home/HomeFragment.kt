package com.example.upyourpartyandroid.ui.fragments.home

import android.os.Bundle
import android.view.View
import com.example.android_nav.AppNavigator
import com.example.android_nav.NavigationHolder
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.databinding.FragmentHomeBinding
import com.example.upyourpartyandroid.ui.fragments.base.BaseFragment
import com.example.upyourpartyandroid.ui.fragments.base.BaseRequestFragment
import com.example.upyourpartyandroid.ui.fragments.categories.CategoriesFragment
import com.example.upyourpartyandroid.ui.fragments.favorites.FavoritesFragment
import com.example.upyourpartyandroid.ui.fragments.messages.MessagesFragment
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.MyAdvertisementsFragment
import com.example.upyourpartyandroid.ui.fragments.profile.ProfileFragment
import com.example.upyourpartyandroid.ui.views.ViewUtils.setItemSelectedListener
import javax.inject.Inject

class HomeFragment : BaseRequestFragment<FragmentHomeBinding, HomeViewModel>(
    HomeViewModel::class,
    FragmentHomeBinding::inflate
) {

    @Inject
    lateinit var navigationHolder: NavigationHolder

    private val navigator by lazy {
        AppNavigator(
            requireActivity(),
            R.id.homeContainer,
            childFragmentManager,
            initialFragment = CategoriesFragment()
        )
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setupNavigator(navigator, HomeViewModel::class.java.name)
    }

    override fun onPause() {
        navigationHolder.removeNavigator(HomeViewModel::class.java.name)
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupViews()
    }

    private fun setupViews() = with(viewModel) {
        binding.bottomNav.setItemSelectedListener(R.id.categories) { item ->
            when(item.itemId) {
                R.id.categories -> navigateToCategories()
                R.id.favorites -> navigateToFavorites()
                R.id.profile -> navigateToProfile()
                R.id.advertisementsManager -> navigateToMyAdvertisements()
                R.id.chat -> navigateToMessages()
            }
        }
    }

}