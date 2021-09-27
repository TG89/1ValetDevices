package com.example.a1valetdevices.util.toothpick

import android.app.Activity
import toothpick.config.Module
import toothpick.ktp.binding.bind


class ActivityModule(activity: Activity): Module() {
    init {
        bind<DevicesProvider>().toClass<DevicesProvider>().singleton()
    }
}