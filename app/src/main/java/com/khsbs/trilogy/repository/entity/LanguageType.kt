package com.khsbs.trilogy.repository.entity

enum class LanguageType(val displayName: String, val papagoCode: String, val kakaoiCode: String, val googleCode: String) {
    KOR("한국어", "ko", "kr", "ko"),
    ENG("영어", "en", "en", "en"),
    ZHCN("중국어 간체", "zh-CN", "cn", "zh-CN"),
    JAP("일본어", "jp", "jp", "ja")
}