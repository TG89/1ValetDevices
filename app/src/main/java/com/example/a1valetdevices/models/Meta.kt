package com.example.a1valetdevices.models

import retrofit2.Response

data class Meta (
    var latestUpdate: Long? = null,
    var retrievalDate: Long? = null,
    var count: Long? = null
) {

    // Constructor to handle data pulled from API
    constructor(data: Response<DevicesResponse>): this() {
        createMetaObjectFromResponse(data)
    }
    constructor(data: Meta): this() {
        createMetaObject(data)
    }

    private fun createMetaObjectFromResponse(data: Response<DevicesResponse>) {
        data.body()?.meta?.let { mainData ->
            latestUpdate = mainData.latestUpdate
            retrievalDate = mainData.retrievalDate
            count = mainData.count
        }
    }

    private fun createMetaObject(data: Meta) {
        data.latestUpdate?.let { latestUpdate = it }
        data.retrievalDate?.let { retrievalDate = it }
        data.count?.let { count = it }
    }
}
