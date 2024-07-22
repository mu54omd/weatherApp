package com.musashi.weatherapp.domain.model

enum class ApiError(val message: String){
    NetworkError("Network Error"),
    UnknownError("Unknown Error"),
    UnknownResponse("Unknown Response")
}