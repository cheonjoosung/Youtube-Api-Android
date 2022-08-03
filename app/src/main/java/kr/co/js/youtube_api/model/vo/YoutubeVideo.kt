package kr.co.js.youtube_api.model.vo

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class YoutubeVideo(
    @SerializedName("kind")
    @Expose
    val kind: String,
    @SerializedName("etag")
    @Expose
    val etag: String,
    @SerializedName("nextPageToken")
    @Expose
    val nextPageToken: String,
    @SerializedName("regionCode")
    @Expose
    val regionCode: String,
    @SerializedName("pageInfo")
    @Expose
    val pageInfo: PageInfo,
    @SerializedName("items")
    @Expose
    val items: List<Items>?
) : Parcelable

@Parcelize
data class PageInfo(
    @SerializedName("totalResults")
    @Expose
    val totalResults: Int,
    @SerializedName("resultsPerPage")
    @Expose
    val resultsPerPage: Int
) : Parcelable

@Parcelize
data class Items(
    @SerializedName("id")
    @Expose
    val id: Id,
    @SerializedName("snippet")
    @Expose
    val snippet: Snippet
) : Parcelable

@Parcelize
data class Id(
    @SerializedName("kind")
    @Expose
    val kind: String,
    @SerializedName("videoId")
    @Expose
    val videoId: String
) : Parcelable

@Parcelize
data class Snippet(
    @SerializedName("publishedAt")
    @Expose
    val publishedAt: String,
    @SerializedName("channelId")
    @Expose
    val channelId: String,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("thumbnails")
    @Expose
    val thumbnails: ThumbNail,
    @SerializedName("publishTime")
    @Expose
    val publishTime: String,
    @SerializedName("channelTitle")
    @Expose
    val channelTitle: String,
) : Parcelable

@Parcelize
data class ThumbNail(
    @SerializedName("medium")
    @Expose
    val medium: Medium
) : Parcelable

@Parcelize
data class Medium(
    @SerializedName("url")
    @Expose
    val url: String,
    @SerializedName("width")
    @Expose
    val width: Int,
    @SerializedName("height")
    @Expose
    val height: Int
) : Parcelable