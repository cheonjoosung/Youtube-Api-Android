package kr.co.js.youtube_api.data.model

data class SearchRequestParams(
    val searchKeyword: String,
    val nextPageToken: String,
    val type: SearchType
)

enum class SearchType {
    First, More
}