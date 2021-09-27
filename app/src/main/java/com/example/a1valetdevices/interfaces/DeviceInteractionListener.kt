package com.example.a1valetdevices.interfaces

import com.example.a1valetdevices.models.Device

interface DeviceInteractionListener {
    fun displayDeviceDetails(device: Device)
}