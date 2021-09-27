package com.example.a1valetdevices.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1valetdevices.models.Device
import com.example.a1valetdevices.models.DevicesResponse
import com.example.a1valetdevices.models.Meta
import com.example.a1valetdevices.models.Platform
import com.example.a1valetdevices.util.toothpick.DevicesProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import toothpick.ktp.delegate.lazy

class DevicesHomeViewModel: ViewModel() {

    private val _deviceResponseLiveData: MutableLiveData<DevicesResponse> = MutableLiveData()
    val deviceResponseLiveData: MutableLiveData<DevicesResponse> = _deviceResponseLiveData

    private val devicesProvider by lazy<DevicesProvider>()

    init {
        retrieveAllDevices()
    }

    private fun retrieveAllDevices() {
        viewModelScope.launch(Dispatchers.IO) {
//            devicesProvider.getAllDevicesList()?.let {
//                deviceResponseLiveData.postValue(it)
//            }
            val dummyData = getDummyData()
            deviceResponseLiveData.postValue(dummyData)
        }
    }

    private fun getDummyData(): DevicesResponse? {
        val devices = arrayListOf<Device>()
        devices.apply {
            add(Device(
                id = 687345L,
                name = "Samsung Galaxy s10",
                platform = Platform.ANDROID,
                releaseDate = 849389L,
                os = "API 26",
                screenSize = "600x900",
                description = "slim, sleek, and stylish",
                imageUrl = "madeup.com/link"))
            add(Device(
                id = 333345L,
                name = "Samsung Galaxy s9",
                platform = Platform.ANDROID,
                releaseDate = 834389L,
                os = "API 26",
                screenSize = "600x900",
                description = "slim, sleek, and stylish",
                imageUrl = "madeup.com/link"))
            add(Device(
                id = 444345L,
                name = "iPhone 11",
                platform = Platform.IOS,
                releaseDate = 573498379L,
                os = "OS 11",
                screenSize = "600x900",
                description = "rose gold",
                imageUrl = "madeup.com/link"))
            add(Device(
                id = 555345L,
                name = "Google Pixel 3",
                platform = Platform.ANDROID,
                releaseDate = 849389L,
                os = "API 27",
                screenSize = "700x1200",
                description = "slim, sleek, and stylish",
                imageUrl = "madeup.com/link"))
            add(Device(
                id = 666345L,
                name = "OnePlus X",
                platform = Platform.ANDROID,
                releaseDate = 8974534L,
                os = "API 26",
                screenSize = "600x900",
                description = "slim, sleek, and stylish",
                imageUrl = "madeup.com/link"))
            add(Device(
                id = 777345L,
                name = "iPhone 12",
                platform = Platform.IOS,
                releaseDate = 2984329L,
                os = "OS 12",
                screenSize = "900x1200",
                description = "Rose gold",
                imageUrl = "madeup.com/link"))
        }
        val meta = Meta(retrievalDate = 80989809L, latestUpdate = 8987989L, count = 6)
        return DevicesResponse(devices = devices, meta = meta)
    }

    fun retrieveAndroidDevices() {
        viewModelScope.launch(Dispatchers.IO) {
            devicesProvider.getAllDevicesList()?.let {
                deviceResponseLiveData.postValue(it)
            }
        }
    }

    fun retrieveIosDevices() {
        viewModelScope.launch(Dispatchers.IO) {
            devicesProvider.getAllDevicesList()?.let {
                deviceResponseLiveData.postValue(it)
            }
        }
    }

    fun searchDevice(deviceName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            devicesProvider.searchDevice(deviceName)?.let {
                deviceResponseLiveData.postValue(it)
            }
        }
    }

}