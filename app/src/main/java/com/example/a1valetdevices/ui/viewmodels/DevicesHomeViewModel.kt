package com.example.a1valetdevices.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1valetdevices.models.*
import com.example.a1valetdevices.util.toothpick.DevicesProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import toothpick.ktp.delegate.lazy

class DevicesHomeViewModel: ViewModel() {

    private val _deviceHomeState: MutableLiveData<DeviceHomeStates> = MutableLiveData()
    val deviceHomeState: MutableLiveData<DeviceHomeStates> = _deviceHomeState

    private val devicesProvider by lazy<DevicesProvider>()

    init {
        retrieveAllDevices()
    }

    fun handle(event: Event) = when(event) {
        is Event.DeviceSelected         ->  updateDeviceHomeState(DeviceHomeStates.DisplayDeviceDetailsState(event.device))
        is Event.DevicceSearchQueried   ->  searchDevice(event.searchQuery)
    }

    private fun retrieveAllDevices() {
        viewModelScope.launch(Dispatchers.IO) {
            /**
             * Since no REST API services were provided nor any online
             * data was given, the below code is commented out
             * to show what/how we would have pulled. Dummy
             * data has been provided
             */
//            devicesProvider.getAllDevicesList()?.let {
//                deviceResponseLiveData.postValue(it)
//            }
            val dummyData = getDummyData()
            updateDeviceHomeState(DeviceHomeStates.InitialState(dummyData))
        }
    }

    private fun searchDevice(deviceName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            /**
             * Since no REST API services were provided nor any online
             * data was given, the below code is commented out
             * to show what/how we would have pulled. Dummy
             * data has been provided
             */
//            devicesProvider.searchDevice(deviceName)?.let {
//                deviceResponseLiveData.postValue(it)
//            }
            val dummyData = getDummyData()
            var queryResults = listOf<Device>()
            dummyData.devices?.let { devices ->
                devices
                    .filter {
                        if(it.name!!.contains(deviceName, ignoreCase = true)) {
                            queryResults = queryResults.plus(it)
                        }
                        true
                    }
            }
            updateDeviceHomeState(DeviceHomeStates.DisplayQueryResultsState(queryResults))
        }
    }

    private fun updateDeviceHomeState(state: DeviceHomeStates) {
        viewModelScope.launch {
            deviceHomeState.postValue(state)
        }
    }

    private fun getDummyData(): DevicesResponse {
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
                imageUrl = "https://m-cdn.phonearena.com/images/phones/75312-940/Samsung-Galaxy-S10.jpg"))
            add(Device(
                id = 333345L,
                name = "Samsung Galaxy s9",
                platform = Platform.ANDROID,
                releaseDate = 834389L,
                os = "API 26",
                screenSize = "600x900",
                description = "slim, sleek, and stylish"/*,
                imageUrl = "madeup.com/link"*/))
            add(Device(
                id = 444345L,
                name = "iPhone 11",
                platform = Platform.IOS,
                releaseDate = 8974534L,
                os = "OS 11",
                screenSize = "600x900",
                description = "rose gold",
                imageUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/MWYY2_AV3?wid=1144&hei=1144&fmt=jpeg&qlt=80&.v=1590871478000"))
            add(Device(
                id = 555345L,
                name = "Google Pixel 3",
                platform = Platform.ANDROID,
                releaseDate = 849389L,
                os = "API 27",
                screenSize = "700x1200",
                description = "slim, sleek, and stylish",
                imageUrl = "https://phonesdata.com/files/models/Google-Pixel-3-780.jpg"))
            add(Device(
                id = 666345L,
                name = "OnePlus X",
                platform = Platform.ANDROID,
                releaseDate = 2984329L,
                os = "API 26",
                screenSize = "600x900",
                description = "slim, sleek, and stylish",
                imageUrl = "https://i.ebayimg.com/thumbs/images/g/nGEAAOSwTAZdFGx-/s-l300.jpg"))
            add(Device(
                id = 777345L,
                name = "iPhone 12",
                platform = Platform.IOS,
                releaseDate = 573498379L,
                os = "OS 12",
                screenSize = "900x1200",
                description = "Rose gold",
                imageUrl = "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-12-pro-blue-witb?wid=312&hei=680&fmt=jpeg&qlt=80&.v=1600983327000"))
        }
        val meta = Meta(retrievalDate = 80989809L, latestUpdate = 8987989L, count = 6)
        return DevicesResponse(devices = devices, meta = meta)
    }

}