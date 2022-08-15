package com.example.presentation.trend

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.VideoInfo
import com.example.presentation.R
import com.example.utils.VideoUtil

class TrendVideoAdapter(
    private val trendList: MutableList<VideoInfo>,
) : RecyclerView.Adapter<TrendVideoAdapter.TrendViewHolder>() {

    var onVideoClick: ((VideoInfo) -> Unit)? = null
    var onVideoLongClick: ((VideoInfo) -> Unit)? = null
    var onVideoMoreClick: ((VideoInfo, View) -> Unit)? = null

    val utils = VideoUtil()

    class TrendViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val clTrendItem: ConstraintLayout = view.findViewById(R.id.cl_trend_item)

        val tvVideoTitle: TextView = view.findViewById(R.id.tv_video_title)
        val tvVideoChannelName: TextView = view.findViewById(R.id.tv_video_channel_name)
        val tvVideoDate: TextView = view.findViewById(R.id.tv_video_date)
        val tvVideoViewCount: TextView = view.findViewById(R.id.tv_video_view_count)
        val tvDuration: TextView = view.findViewById(R.id.tv_video_duration)

        val ivChannelLogo: ImageView = view.findViewById(R.id.iv_channel_logo)

        val ivThumbnail: ImageView = view.findViewById(R.id.iv_video_thumbnail)
        val ivMore: ImageView = view.findViewById(R.id.iv_more)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_trend_video, parent, false)
        return TrendViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {

        holder.apply {
            val video = trendList[position]
            Log.e("CJS", "trendVideo $video")

            tvVideoTitle.text = video.title
            tvVideoChannelName.text = video.channelTitle
            tvVideoDate.text = utils.convertPublishedDate(video.publishedAt)
            tvVideoViewCount.text = utils.convertViewCount(video.viewCount)

            Glide.with(holder.itemView.context)
                .load(video.channelImgUrl)
                .into(ivChannelLogo)

            tvDuration.text = utils.convertDurationToHHMMSS(video.duration)

            Glide.with(holder.itemView.context)
                .load(video.imgUrl)
                .into(ivThumbnail)

            clTrendItem.apply {
                setOnClickListener {
                    onVideoClick?.invoke(video)
                }

                setOnLongClickListener {
                    onVideoLongClick?.invoke(video)
                    true
                }
            }

            ivMore.setOnClickListener {
                onVideoMoreClick?.invoke(video, it)
            }
        }
    }

    override fun getItemCount(): Int = trendList.size

    fun updateList(items: List<VideoInfo>) {
        items.let {
            val diffCallback = DiffUtilCallback(this.trendList, items)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            this.trendList.run {
                clear()
                addAll(items)
                diffResult.dispatchUpdatesTo(this@TrendVideoAdapter)
            }
        }
    }
}