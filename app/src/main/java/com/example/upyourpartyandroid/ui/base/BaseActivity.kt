package com.example.upyourpartyandroid.ui.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.upyourpartyandroid.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseActivity<VM : ViewModel, VB : ViewBinding>(
    private val vmType: KClass<VM>,
    private val vbFactory: (LayoutInflater) -> VB
) : DaggerAppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var viewModel: VM

    protected lateinit var viewBinding: VB

    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = vbFactory.invoke(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this, viewModelFactory)[vmType.java]
    }

    protected fun showLoadingIndicator() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setView(layoutInflater.inflate(R.layout.loading_indicator, null, false))
        dialogBuilder.setCancelable(false)
        alertDialog = dialogBuilder.create()
        alertDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog?.show()
    }

    protected fun hildeLoadingIndicator() {
        alertDialog?.dismiss()
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    protected fun showMessageDialog(message: String) {

    }

}