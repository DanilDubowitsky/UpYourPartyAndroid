package com.example.upyourpartyandroid.ui.views

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import androidx.annotation.StringRes
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.ui.views.ViewUtils.shake

object ViewUtils {

    inline fun View.setClickListener(crossinline onClick: () -> Unit) = this.setClickListener(true, onClick)

    inline fun View.setClickListener(shouldDisable: Boolean = true, crossinline onClick: () -> Unit) {
        this.setOnClickListener {
            onClick.invoke()
            if(!shouldDisable){
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
        if(this.visibility != visibility) {
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

}