package kr.co.js.youtube_search.model.vo

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class YoutubeVideoInfo(
    @SerializedName("kind")
    @Expose
    val kind: String,
    @SerializedName("etag")
    @Expose
    val etag: String,
    @SerializedName("items")
    @Expose
    val items: List<TrendItem>?
) : Parcelable

@Parcelize
data class TrendItem(
    @SerializedName("kind")
    @Expose
    val kind: String,
    @SerializedName("etag")
    @Expose
    val etag: String,
    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("snippet")
    @Expose
    val snippet: Snippet,

    @SerializedName("tags")
    @Expose
    val tags: List<String>,

    @SerializedName("contentDetails")
    @Expose
    val contentDetails: ContentDetails,

    @SerializedName("statistics")
    @Expose
    val statistics: Statistics
) : Parcelable

@Parcelize
data class ContentDetails(
    @SerializedName("duration")
    @Expose
    val duration: String
) : Parcelable

@Parcelize
data class Statistics(
    @SerializedName("viewCount")
    @Expose
    val viewCount: String? = ""
) : Parcelable