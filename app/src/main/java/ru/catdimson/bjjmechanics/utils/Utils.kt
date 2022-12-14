package ru.catdimson.bjjmechanics.utils

import retrofit2.Response
import java.util.regex.Pattern

fun extractYoutubeId(ytUrl: String): String {
    var vId: String? = null
    val pattern = Pattern.compile(
        "(?:https?:\\/\\/)?(?:www\\.)?youtu\\.?be(?:\\.com)?\\/?.*(?:watch|embed)?(?:.*v=|v\\/|\\/)([\\w\\-_]+)\\&?"
    )
    val matcher = pattern.matcher(ytUrl)
    if (matcher.matches()) {
        vId = matcher.group(1)
    }
    return vId!!
}

fun <T> extractIdFromHeaderLocation(response: Response<T>): Int? {
    val location = response.headers().get("Location")
    val patternId = """.*\/(?<id>\d+)$""".toRegex()

    if (location != null) {
        val matcher = patternId.matchEntire(location)

        if (matcher != null) {
            val groups = matcher.groups as? MatchNamedGroupCollection

            if (groups != null) {
                return groups["id"]?.value?.toInt()
            }
            return null
        }
        return null
    }
    return null
}