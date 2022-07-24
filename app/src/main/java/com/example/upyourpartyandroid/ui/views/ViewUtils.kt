package com.example.upyourpartyandroid.ui.views

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import androidx.annotation.AnimRes
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.ui.views.ViewUtils.shake
import com.google.android.material.bottomnavigation.BottomNavigationView

object ViewUtils {

    fun ViewGroup.inflate(layoutId: Int): View {
        return LayoutInflater.from(context).inflate(layoutId, this, false)
    }

    inline fun View.setClickListener(crossinline onClick: () -> Unit) =
        this.setClickListener(true, onClick)

    inline fun View.setClickListener(
        shouldDisable: Boolean = true,
        crossinline onClick: () -> Unit
    ) {
        this.setOnClickListener {
            onClick.invoke()
            if (!shouldDisable) {
                return@setOnClickListener
            }
            this.isEnabled = false
            this.postDelayed({
                this.isEnabled = true
            }, 300)
        }
    }

    fun EditText.textToUpperCase() {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                removeTextChangedListener(this)
                setText(p0?.toString()?.uppercase())
                setSelection(length())
                addTextChangedListener(this)
            }
        })
    }

    fun View.tryChangeVisibility(visibility: Int) {
        if (this.visibility != visibility) {
            this.visibility = visibility
        }
    }

    fun EditText.showError(error: String) {
        shake()
        this.error = error
    }

    fun EditText.showError(@StringRes stringId: Int) {
        shake()
        this.error = this.context.getString(stringId)
    }

    fun View.shake() {
        val anim = AnimationUtils.loadAnimation(this.context, R.anim.shake)
        startAnimation(anim)
    }

    inline fun BottomNavigationView.setItemSelectedListener(
        firstItemId: Int,
        crossinline onItemSelected: (item: MenuItem) -> Unit
    ) {
        var selectedItem: MenuItem = menu.findItem(selectedItemId)
        selectedItem.isEnabled = false
        this.setOnItemSelectedListener { item ->
            selectedItem.isEnabled = true
            selectedItem = item
            item.isEnabled = false
            onItemSelected(item)
            return@setOnItemSelectedListener true
        }
    }

    fun View.changeVisibilityWithAnimation(@AnimRes animationRes: Int, visibility: Int) {
        val anim = AnimationUtils.loadAnimation(context, animationRes)
        this.visibility = visibility
        startAnimation(anim)
    }

    inline fun ViewPager2.setOnPageChangeListener(crossinline onPageChange: (page: Int) -> Unit) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                onPageChange.invoke(position)
            }
        })
    }

}