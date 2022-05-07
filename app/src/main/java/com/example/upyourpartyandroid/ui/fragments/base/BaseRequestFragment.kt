package com.example.upyourpartyandroid.ui.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.upyourpartyandroid.ui.base.ViewModelFactory
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseRequestFragment<VB: ViewBinding, VM: ViewModel>(
    private val viewModelClass: KClass<VM>,
    vbFactory: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : BaseFragment<VB>(vbFactory) {

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass.java]
    }

}