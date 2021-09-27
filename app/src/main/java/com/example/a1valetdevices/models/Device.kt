package com.example.a1valetdevices.models

enum class Platform {
    IOS,
    ANDROID
}

data class Device (
    var id: Long? = null,
    var name: String? = null,
    var platform: Platform? = null,
    var releaseDate: Long? = null,
    var os: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,
) {

    // Constructor to handle data pulled from API
    constructor(data: Device): this() {
        createWeatherResponseObject(data)
    }

    private fun createWeatherResponseObject(data: Device) {
        data.id?.let { id = it }
        data.name?.let { name = it }
        data.platform?.let { platform = it }
        data.releaseDate?.let { releaseDate = it }
        data.os?.let { os = it }
        data.description?.let { description = it }
        data.imageUrl?.let { imageUrl = it }
    }
}
