package com.example.a1valetdevices.network.requests

import android.util.Log
import com.example.a1valetdevices.models.DevicesResponse
import com.example.a1valetdevices.network.APIClient
import kotlinx.coroutines.CompletableDeferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IosDevicesRequest {

    private val TAG = IosDevicesRequest::class.java.simpleName

    suspend fun getList(minOs: Int): DevicesResponse? {
        val result = CompletableDeferred<DevicesResponse?>()

        APIClient.androidDevicesApiObject
            .getIosDevicesList(minOs)
            .enqueue(object: Callback<DevicesResponse> {
                override fun onResponse(call: Call<DevicesResponse>, response: Response<DevicesResponse>) {
                    response.body()?.let {
                        result.complete(it)
                    }
                }

                override fun onFailure(call: Call<DevicesResponse>, t: Throwable) {
                    Log.e(TAG, t.message.toString())
                }
            })

        return result.await()
    }

}