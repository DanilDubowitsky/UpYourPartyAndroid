package com.example.upyourpartyandroid.ui.fragments.my_advertisements.create.recycler

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.usecase.advertisement.IProgressListener
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.ui.views.ViewUtils.changeVisibilityWithAnimation
import com.example.upyourpartyandroid.ui.views.ViewUtils.inflate
import com.example.upyourpartyandroid.ui.views.ViewUtils.setClickListener
import com.example.upyourpartyandroid.ui.views.ViewUtils.tryChangeVisibility
import javax.inject.Inject

class ImagesAdapter @Inject constructor(
    private val progressListener: IProgressListener
) : RecyclerView.Adapter<ImagesViewHolder>() {

    private var data: List<String> = emptyList()

    fun setList(list: List<String>) {
        this.data = list
        notifyDataSetChanged()
    }

    var onHolderClick: ((position: Int) -> Unit)? = null

    var onDeleteClick: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val view = parent.inflate(R.layout.view_holder_image)
        return ImagesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val item = data[position]

        if (item.isEmpty()) {
            val defColor = holder.itemView.context.getColor(R.color.lightGray)
            holder.binding.imageCard.setCardBackgroundColor(defColor)
            holder.binding.addImage.setImageResource(R.drawable.ic_add)
            holder.binding.image.tryChangeVisibility(View.GONE)
        } else {
            Glide.with(holder.binding.image)
                .load(item.toUri())
                .centerCrop()
                .into(holder.binding.image)
            holder.binding.image.tryChangeVisibility(View.VISIBLE)
            holder.binding.addImage.tryChangeVisibility(View.GONE)
        }
        progressListener.setProgressListener(position) { progress ->
            if(progress != -1 && progress != 100 && progress != 0) {
                holder.binding.progressBar.tryChangeVisibility(View.VISIBLE)
                holder.binding.progressBar.setProgress(progress, true)
            } else {
                if(progress == 100) holder.binding.deleteImage.tryChangeVisibility(View.VISIBLE)
                holder.binding.progressBar.tryChangeVisibility(View.GONE)
            }
        }

        holder.binding.deleteImage.setClickListener {
            holder.binding.deleteImage.tryChangeVisibility(View.GONE)
            onDeleteClick?.invoke(position)
        }

        holder.itemView.setClickListener {
            onHolderClick?.invoke(position)
        }

    }

    override fun getItemCount(): Int = data.size

}