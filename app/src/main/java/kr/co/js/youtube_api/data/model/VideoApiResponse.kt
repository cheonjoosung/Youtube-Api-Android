package kr.co.js.youtube_api.data.model

import java.lang.Exception

/**
 * 네트워크 응답 객체
 */
sealed class VideoApiResponse<T>(
    data: T? = null,
    exception: Exception? = null
) {
    data class Success<T>(val data: T) : VideoApiResponse<T>(data, null)

    data class Error<T>(val exception: Exception) : VideoApiResponse<T>(null, exception)
}