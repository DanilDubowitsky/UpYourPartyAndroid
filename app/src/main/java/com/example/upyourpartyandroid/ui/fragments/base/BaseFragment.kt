package com.example.upyourpartyandroid.ui.fragments.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.example.upyourpartyandroid.R
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment

abstract class BaseFragment<VB: ViewBinding>(
    private val vbFactory: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : DaggerFragment() {

    private var viewBinding: VB? = null
    protected val binding get() = viewBinding!!

    private var alertDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = vbFactory.invoke(inflater, container, false)
        return binding.root
    }

    protected fun showLoadingIndicator() {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        dialogBuilder.setView(layoutInflater.inflate(R.layout.loading_indicator, null, false))
        dialogBuilder.setCancelable(false)
        alertDialog = dialogBuilder.create()
        alertDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog?.show()
    }

    protected fun hideLoadingIndicator() {
        alertDialog?.dismiss()
    }

    protected fun showSnackBar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

    protected fun showSnackBar(@StringRes message: Int) {
        showSnackBar(getString(message))
    }

    override fun onDestroyView() {
        viewBinding = null
        super.onDestroyView()
    }

}
