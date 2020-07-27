package com.khsbs.trilogy.repository.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class InterpretHistory (
    @PrimaryKey val inputText: String,
    val sourceLang: String,
    val targetLang: String,
    val resultPapago: String?,
    val resultKakaoi: String?,
    val resultGoogle: String?
)