package com.chorokbul.datastore.model

data class AppPrefs(
    val isSoundSignalDownloaded: Boolean,
    val isCrosswalkDownloaded: Boolean,
    val dbVersion: Int
)
