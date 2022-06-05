package com.example.upyourpartyandroid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object Utils {

    fun String.formatPhoneToString(): String {
        return replace("(", "")
            .replace(")", "")
            .replace(" ", "")
            .replace("-", "")
    }

    fun Fragment.argumentsBoolean(
        defaultValue: Boolean = false,
        key: (KProperty<*>) -> String = KProperty<*>::name
    ) = object : ReadWriteProperty<Any, Boolean> {

        override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
            return getBundle().getBoolean(key(property))
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
            getBundle().putBoolean(key(property), value)
        }
    }

    fun Bundle.putNullableLong(key: String?, value: Long?) =
        this.putLong(key, value ?: Long.MAX_VALUE)

    fun Bundle.getLongOrNull(key: String?): Long? =
        this.getLong(key).takeIf { it != Long.MAX_VALUE }

    fun Fragment.argumentsNullableLong(
        defaultValue: Long? = null,
        key: (KProperty<*>) -> String = KProperty<*>::name
    ) = object : ReadWriteProperty<Any, Long?> {

        override fun getValue(thisRef: Any, property: KProperty<*>): Long? {
            return requireArguments().getLongOrNull(key(property))
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Long?) {
            getBundle().putNullableLong(key(property), value)
        }
    }

    private fun Fragment.getBundle(): Bundle {
        return arguments ?: Bundle().also { newArguments ->
            arguments = newArguments
        }
    }

}
