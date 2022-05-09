package com.example.upyourpartyandroid.ui.base

import androidx.recyclerview.widget.DiffUtil

abstract class SimpleDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areContentTheSame(oldList[oldItemPosition], newList[newItemPosition])
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemTheSame(oldList[oldItemPosition], newList[newItemPosition])
    }

    abstract fun areContentTheSame(oldItem: T, newItem: T): Boolean

    abstract fun areItemTheSame(oldItem: T, newItem: T): Boolean
}