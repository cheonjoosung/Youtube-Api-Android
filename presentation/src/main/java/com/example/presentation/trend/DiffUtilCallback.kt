package com.example.presentation.trend

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.remote.VideoInfo

class DiffUtilCallback(
    private val oldList: List<VideoInfo>,
    private val newList: List<VideoInfo>,
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.videoId == newItem.videoId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].videoId == newList[newItemPosition].videoId
                //oldList[oldItemPosition].isFavorite == newList[newItemPosition].isFavorite
}