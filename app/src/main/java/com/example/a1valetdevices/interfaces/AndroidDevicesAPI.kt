package com.example.a1valetdevices.interfaces

import com.example.a1valetdevices.models.DevicesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This is a sample API, to just show a sample REST
 */
interface AndroidDevicesAPI {

    /**
     * API to get all devices related information
     * note: made up API call examples
     */
    @GET("devices?")
    fun getAllDevicesList(): Call<DevicesResponse>


    /**
     * Get android devices
     * made up example
     *
     * @param minApilvl   minimum api level for android devices
     */
    @GET("devices?type=android")
    fun getAndroidDeviceList(@Query("minApiLvl") minApiLvl: Int): Call<DevicesResponse>

    /**
     * Get ios devices
     * made up example
     *
     * @param minOs   minimum os for iOS devices
     */
    @GET("devices?type=ios")
    fun getIosDevicesList(@Query("minOs") minOs: Int): Call<DevicesResponse>

    /**
     * Get list of devices matching given search criteria
     * made up example
     *
     * @param search   name of device to search
     */
    @GET("devices")
    fun searchDevices(@Query("search") search: String): Call<DevicesResponse>

}