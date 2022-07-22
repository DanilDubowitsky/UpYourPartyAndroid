package com.example.upyourpartyandroid.ui.dialogs.my_advertisements

import android.os.Bundle
import android.view.View
import com.example.android_nav.NavigationScreen
import com.example.upyourpartyandroid.databinding.DialogMyAdvertisementsActionsBinding
import com.example.upyourpartyandroid.navigation.IRouter
import com.example.upyourpartyandroid.ui.dialogs.base.BaseBottomSheetDialog
import com.example.upyourpartyandroid.ui.views.ViewUtils.setClickListener
import javax.inject.Inject

class MyAdvertisementsActionsDialog : BaseBottomSheetDialog<DialogMyAdvertisementsActionsBinding>(
    DialogMyAdvertisementsActionsBinding::inflate
) {

    @Inject
    protected lateinit var router: IRouter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupListeners()
    }

    private fun setupListeners() = with(binding) {
        editContainer.setClickListener {
            router.sendResult(NavigationScreen.AdvertisementManager.MyAdvertisementsActions.OnEditClick, Unit)
            dismiss()
        }
        deleteContainer.setClickListener {
            router.sendResult(NavigationScreen.AdvertisementManager.MyAdvertisementsActions.OnDeleteClick, Unit)
            dismiss()
        }
    }
}