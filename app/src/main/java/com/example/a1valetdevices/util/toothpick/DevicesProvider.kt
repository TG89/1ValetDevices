package com.example.a1valetdevices.util.toothpick

import com.example.a1valetdevices.models.DevicesResponse
import com.example.a1valetdevices.network.requests.AllDevicesRequest
import com.example.a1valetdevices.network.requests.AndroidDevicesRequest
import com.example.a1valetdevices.network.requests.IosDevicesRequest
import com.example.a1valetdevices.network.requests.SearchDevicesRequest
import toothpick.InjectConstructor

@InjectConstructor
class DevicesProvider {

    suspend fun getAllDevicesList(): DevicesResponse? {
        return AllDevicesRequest().getList()
    }

    suspend fun getAndroidDevicesList(minApiLvl: Int): DevicesResponse? {
        return AndroidDevicesRequest().getList(minApiLvl)
    }

    suspend fun getIosDevicesList(minOs: Int): DevicesResponse? {
        return IosDevicesRequest().getList(minOs)
    }

    suspend fun searchDevice(deviceName: String): DevicesResponse? {
        return SearchDevicesRequest().getList(deviceName)
    }
}