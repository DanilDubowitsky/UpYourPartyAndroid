package com.example.upyourpartyandroid.ui.fragments.profile

import android.os.Bundle
import android.view.View
import com.example.upyourpartyandroid.databinding.FragmentProfileBinding
import com.example.upyourpartyandroid.ui.fragments.base.BaseFragment
import com.example.upyourpartyandroid.ui.fragments.base.BaseRequestFragment
import com.example.upyourpartyandroid.ui.views.ViewUtils.setClickListener

class ProfileFragment : BaseRequestFragment<FragmentProfileBinding, ProfileViewModel>(
    ProfileViewModel::class,
    FragmentProfileBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupListeners()
    }

    private fun setupListeners() = with(binding) {
        logOutBtn.setClickListener(viewModel::logOut)
    }

}