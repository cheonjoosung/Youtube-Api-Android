package kr.co.js.youtube_api.common

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.HashMap

class VideoUtil {

    companion object {
        const val MIN = 60
        const val HOUR = 60 * 60
        const val DAY = 60 * 60 * 24
        const val MONTH = 60 * 60 * 24 * 30
        const val YEAR = 60 * 60 * 24 * 365
    }

    fun convertDurationToHHMMSS(duration: String): String {

        val regexMap: HashMap<String, String> = HashMap()

        val regex2two = "(?<=[^\\d])(\\d)(?=[^\\d])"
        val two = "0$1"

        regexMap["PT(\\d\\d)S"] = "00:$1"
        regexMap["PT(\\d\\d)M"] = "$1:00"
        regexMap["PT(\\d\\d)H"] = "$1:00:00"
        regexMap["PT(\\d\\d)M(\\d\\d)S"] = "$1:$2"
        regexMap["PT(\\d\\d)H(\\d\\d)S"] = "$1:00:$2"
        regexMap["PT(\\d\\d)H(\\d\\d)M"] = "$1:$2:00"
        regexMap["PT(\\d\\d)H(\\d\\d)M(\\d\\d)S"] = "$1:$2:$3"

        val d: String = duration.replace(
            regex2two.toRegex(), two
        )

        getRegex(regexMap, d)?.let {
            return d.replace(it.toRegex(), regexMap[it]!!)
        } ?: return "00:00"

    }

    private fun getRegex(regexMap: HashMap<String, String>, date: String): String? {
        for (r in regexMap.keys) if (Pattern.matches(r, date)) return r
        return null
    }

    /**
     * 100, 1000, 10000, 100_000_000 4단위 사용
     */
    fun convertViewCount(viewCount: String): String {

        val result = StringBuilder()

        try {
            val longViewCount = viewCount.replace(".", "").replace(",", "").toLong()

            when {
                longViewCount < 1000 -> {
                    result.append(longViewCount / 100).append("백회")
                }
                longViewCount < 10_000 -> {
                    result.append(longViewCount / 1_000).append("천회")
                }
                longViewCount < 100_000_000 -> {
                    result.append(longViewCount / 10_000).append("만회")
                }
                else -> {
                    result.append(longViewCount / 100_000_000).append("억회")
                }
            }

        } catch (e: NumberFormatException) {
            return result.toString()
        }

        return result.toString()
    }

    /**
     * ex) 2022-dd-mm 이 만든시점일떄,
     * 현재날짜와 비교하여 며칠전, 2주전, 1달전, 1년전으로 변경
     */
    fun convertPublishedDate(publishedDate: String): String {

        val result = StringBuilder()

        try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT")

            val convertedPublishedTime =
                simpleDateFormat.parse(publishedDate)?.time ?: 0L
            val currentTime = System.currentTimeMillis()

            val diff = (currentTime - convertedPublishedTime) / 1000

            when {
                diff < MIN -> {
                    result.append(diff).append("초전")
                }
                diff < HOUR -> {
                    result.append(diff / MIN).append("분전")
                }
                diff < DAY -> {
                    result.append(diff / HOUR).append("시간전")
                }
                diff < MONTH -> {
                    result.append(diff / DAY).append("일전")
                }
                diff < YEAR -> {
                    result.append(diff / MONTH).append("달전")
                }
                else -> {
                    result.append(diff / YEAR).append("년전")
                }
            }

        } catch (e: ParseException) {
            return ""
        }

        return result.toString()
    }
}