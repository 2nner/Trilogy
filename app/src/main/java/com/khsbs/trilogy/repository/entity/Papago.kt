package com.khsbs.trilogy.repository.entity

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