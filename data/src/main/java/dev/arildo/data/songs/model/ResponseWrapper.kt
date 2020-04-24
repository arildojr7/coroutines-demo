package dev.arildo.data.songs.model

data class ResponseWrapper<T>(
    val resultCount: Int = 0,
    val results: List<T> = emptyList()
)