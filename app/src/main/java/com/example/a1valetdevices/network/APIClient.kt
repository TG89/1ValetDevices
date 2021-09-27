package com.example.a1valetdevices.network

import com.example.a1valetdevices.interfaces.AndroidDevicesAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    private val gson: Gson by lazy {
     GsonBuilder().setLenient().create()
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val androidDevicesRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.EXAMPLE_BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val androidDevicesApiObject: AndroidDevicesAPI by lazy {
        androidDevicesRetrofit.create(AndroidDevicesAPI::class.java)
    }
}