package com.khsbs.trilogy.model

import com.squareup.moshi.Json

data class Kakaoi (
    @Json(name = "translated_text")
    val translatedText: List<List<String>>
)