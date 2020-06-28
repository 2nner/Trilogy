package com.khsbs.trilogy.model

enum class LanguageType(val displayName: String, val papagoCode: String, val kakaoiCode: String) {
    KOR("한국어", "ko", "kr"),
    ENG("영어", "en", "en"),
    ZHCN("중국어 간체", "zh-CN", "cn"),
    ZHTW("중국어 번체", "zh-TW", "cn"),
    JAP("일본어", "jp", "jp")
}