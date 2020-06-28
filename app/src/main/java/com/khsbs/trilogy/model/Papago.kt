package com.khsbs.trilogy.model

data class Papago(
    val message: Message
) {
    data class Message(
        val result: Result
    ) {
        data class Result(
            val translatedText: String
        )
    }
}