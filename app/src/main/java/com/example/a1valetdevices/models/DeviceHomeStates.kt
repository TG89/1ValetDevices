package com.example.a1valetdevices.models

sealed class DeviceHomeStates {
    data class InitialState(val devicesResponse: DevicesResponse): DeviceHomeStates()
    data class DisplayQueryResultsState(val deviceList: List<Device>): DeviceHomeStates()
    data class DisplayDeviceDetailsState(val device: Device): DeviceHomeStates()
}

sealed class Event {
    data class DeviceSelected(val device: Device): Event()
    data class DevicceSearchQueried(val searchQuery: String): Event()
}