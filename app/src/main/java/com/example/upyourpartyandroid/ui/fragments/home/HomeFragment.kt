package com.example.upyourpartyandroid.ui.fragments.home

import android.os.Bundle
import android.view.View
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.databinding.FragmentHomeBinding
import com.example.upyourpartyandroid.ui.fragments.base.BaseFragment
import com.example.upyourpartyandroid.ui.fragments.categories.CategoriesFragment
import com.example.upyourpartyandroid.ui.fragments.favorites.FavoritesFragment
import com.example.upyourpartyandroid.ui.fragments.messages.MessagesFragment
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.MyAdvertisementsFragment
import com.example.upyourpartyandroid.ui.fragments.profile.ProfileFragment
import com.example.upyourpartyandroid.ui.views.ViewUtils.setItemSelectedListener

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupViews()
    }

    private fun setupViews() {
        binding.bottomNav.setItemSelectedListener(R.id.categories) { item ->
            when(item.itemId) {
                R.id.categories -> childFragmentManager.beginTransaction().replace(R.id.homeContainer, CategoriesFragment()).commit()
                R.id.favorites -> childFragmentManager.beginTransaction().replace(R.id.homeContainer, FavoritesFragment()).commit()
                R.id.profile -> childFragmentManager.beginTransaction().replace(R.id.homeContainer, ProfileFragment()).commit()
                R.id.advertisementsManager -> childFragmentManager.beginTransaction().replace(R.id.homeContainer, MyAdvertisementsFragment()).commit()
                R.id.chat -> childFragmentManager.beginTransaction().replace(R.id.homeContainer, MessagesFragment()).commit()
            }
        }
    }

}