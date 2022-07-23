package com.example.domain.preferences

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class BaseSharedPreferences(
    protected val preferences: SharedPreferences
) : IPreferencesContract.IPreferences {

    protected inner class PreferencesString(
        private val defaultValue: String = "",
        private val key: (KProperty<*>) -> String = KProperty<*>::name
    ) : ReadWriteProperty<Any, String?> {

        override fun getValue(thisRef: Any, property: KProperty<*>): String =
            preferences.getString(key(property), defaultValue) ?: defaultValue

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
            preferences.edit()
                .putString(key(property), value)
                .apply()
        }

    }

    protected inner class PreferencesInt(
        private val defaultValue: Int = Int.MAX_VALUE,
        private val key: (KProperty<*>) -> String = KProperty<*>::name
    ) : ReadWriteProperty<Any, Int> {

        override fun getValue(thisRef: Any, property: KProperty<*>): Int =
            preferences.getInt(key(property), defaultValue)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
            preferences.edit()
                .putInt(key(property), value)
                .apply()
        }

    }

    protected inner class PreferencesBoolean(
        private val defaultValue: Boolean = false,
        private val key: (KProperty<*>) -> String = KProperty<*>::name
    ) : ReadWriteProperty<Any, Boolean> {

        override fun getValue(thisRef: Any, property: KProperty<*>): Boolean =
            preferences.getBoolean(key(property), defaultValue)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
            preferences.edit()
                .putBoolean(key(property), value)
                .apply()
        }

    }

    protected inner class PreferencesLong(
        private val defaultValue: Long = Long.MAX_VALUE,
        private val key: (KProperty<*>) -> String = KProperty<*>::name
    ) : ReadWriteProperty<Any, Long> {

        override fun getValue(thisRef: Any, property: KProperty<*>): Long =
            preferences.getLong(key(property), defaultValue)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) {
            preferences.edit()
                .putLong(key(property), value)
                .apply()
        }

    }

    protected inner class PreferencesFloat(
        private val defaultValue: Float = Float.MAX_VALUE,
        private val key: (KProperty<*>) -> String = KProperty<*>::name
    ) : ReadWriteProperty<Any, Float> {

        override fun getValue(thisRef: Any, property: KProperty<*>): Float =
            preferences.getFloat(key(property), defaultValue)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Float) {
            preferences.edit()
                .putFloat(key(property), value)
                .apply()
        }

    }

    override fun clear() {
        preferences.edit().clear().apply()
    }

}