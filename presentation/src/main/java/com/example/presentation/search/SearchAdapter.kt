package com.example.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Video
import com.example.presentation.databinding.ItemVideoBinding
import com.example.utils.VideoUtil

class SearchAdapter(
    private val list: MutableList<Video>
) : RecyclerView.Adapter<SearchAdapter.VideoViewHolder>() {

    var videoClick: ((Video) -> Unit)? = null

    class VideoViewHolder(
        private val binding: ItemVideoBinding,
        private val videoClick: ((Video) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        private val utils = VideoUtil()

        fun bind(video: Video) {
            binding.apply {
                tvVideoTitle.text = video.title
                tvVideoChannelName.text = video.channelTitle
                tvVideoDate.text = utils.convertPublishedDate(video.publishedAt)
                tvVideoViewCount.text = utils.convertViewCount(video.viewCount)
                tvVideoDuration.text = utils.convertDurationToHHMMSS(video.duration)

                Glide.with(itemView.context)
                    .load(video.imgUrl)
                    .into(ivVideoThumbnail)

                binding.clVideoItem.setOnClickListener {
                    videoClick?.invoke(video)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding, videoClick)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addMoreVideoList(addList: MutableList<Video>) {
        val prevSize = list.size
        list.addAll(addList)
        notifyItemRangeChanged(prevSize, list.size)
    }
}