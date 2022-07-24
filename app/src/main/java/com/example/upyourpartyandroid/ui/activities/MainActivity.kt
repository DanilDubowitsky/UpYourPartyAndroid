package com.example.upyourpartyandroid.ui.activities

import android.os.Bundle
import android.view.MenuItem
import com.example.android_nav.AppNavigator
import com.example.android_nav.NavigationHolder
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.databinding.ActivityMainBinding
import com.example.upyourpartyandroid.ui.base.BaseActivity
import com.example.upyourpartyandroid.ui.fragments.home.HomeFragment
import com.example.upyourpartyandroid.ui.fragments.login.LoginFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(
    MainActivityViewModel::class,
    ActivityMainBinding::inflate
) {

    @Inject
    lateinit var navigationHolder: NavigationHolder

    private val navigator by lazy {
        AppNavigator(
            this,
            R.id.fragmentContainer
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_UpYourPartyAndroid)
        super.onCreate(savedInstanceState)
        navigationHolder.setupNavigator(navigator)
        if(savedInstanceState == null) viewModel.checkIsAuthorize()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                viewModel.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setupNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }

}