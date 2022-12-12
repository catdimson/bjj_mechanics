package ru.catdimson.bjjmechanics.utils

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