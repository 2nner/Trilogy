package com.khsbs.trilogy.repository.entity

data class GoogleTrans(
    val data : Data
) {
    data class Data(
        val translations: List<Translations>
    ) {
        data class Translations(
            val translatedText: String
        )
    }
}