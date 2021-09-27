package com.example.a1valetdevices.models

import retrofit2.Response

/**
 * Data class responsible for list of devices data pulled from
 * MadeUpExample `AndroidDevicesAPI` file
 */
data class DevicesResponse (
    var devices: ArrayList<Device>? = null,
    var meta: Meta? = null
) {

    // Constructor to handle data pulled from API
    constructor(data: Response<DevicesResponse>) : this() {
        createWeatherObject(data)
    }

    private fun createWeatherObject(data: Response<DevicesResponse>) {
        meta = Meta(data)
        devices = arrayListOf()
        data.body()?.devices?.let { weatherResponse ->
            weatherResponse.forEach {
                devices!!.add(Device(it))
            }
        }
    }
}

