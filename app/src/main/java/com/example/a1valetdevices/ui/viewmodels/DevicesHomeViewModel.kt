package com.example.a1valetdevices.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1valetdevices.models.Device
import com.example.a1valetdevices.models.DevicesResponse
import com.example.a1valetdevices.util.toothpick.DevicesProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import toothpick.ktp.delegate.lazy

class DevicesHomeViewModel: ViewModel() {

    private val _deviceResponseLiveData: MutableLiveData<DevicesResponse> = MutableLiveData()
    val deviceResponseLiveData: MutableLiveData<DevicesResponse> = _deviceResponseLiveData

//    private val _DeviceListLiveData: MutableLiveData<List<Device>> = MutableLiveData()
//    val deviceListLiveData: MutableLiveData<List<Device>> = _DeviceListLiveData

    private val devicesProvider by lazy<DevicesProvider>()

    init {
        retrieveAllDevices()
    }

    private fun retrieveAllDevices() {
        viewModelScope.launch(Dispatchers.IO) {
            devicesProvider.getAllDevicesList()?.let {
                deviceResponseLiveData.postValue(it)
//                deviceListLiveData.postValue(it.devices)            // UNECESSARY????
            }
        }
    }

    fun retrieveAndroidDevices() {
        viewModelScope.launch(Dispatchers.IO) {
            devicesProvider.getAllDevicesList()?.let {
                deviceResponseLiveData.postValue(it)
//                deviceListLiveData.postValue(it.devices)
            }
        }
    }

    fun retrieveIosDevices() {
        viewModelScope.launch(Dispatchers.IO) {
            devicesProvider.getAllDevicesList()?.let {
                deviceResponseLiveData.postValue(it)
//                deviceListLiveData.postValue(it.devices)
            }
        }
    }

    fun searchDevice(deviceName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            devicesProvider.searchDevice(deviceName)?.let {
                deviceResponseLiveData.postValue(it)
//                deviceListLiveData.postValue(it.devices)
            }
        }
    }

}