package com.polendina.soai.utils

import java.util.regex.Pattern

fun String.parseFKey(): String {
    val pattern = Pattern.compile("\"fkey\":\"(.*?)\"")
    val matcher = pattern.matcher(this)
    return if (matcher.find()) matcher.group(1) else ""
}