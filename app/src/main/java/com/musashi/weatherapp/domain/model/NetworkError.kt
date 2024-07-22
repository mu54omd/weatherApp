package com.musashi.weatherapp.domain.model

data class NetworkError(
    val error: ApiError,
    val t: Throwable
)