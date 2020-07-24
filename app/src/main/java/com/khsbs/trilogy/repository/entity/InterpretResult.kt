package com.khsbs.trilogy.repository.entity

data class InterpretResult (
    var resultPapago: Papago?,
    var resultKakaoi: Kakaoi?,
    var resultGoogleTrans: GoogleTrans?
)

enum class Status {
    SUCCESS, FAILED
}