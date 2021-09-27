package com.example.a1valetdevices.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a1valetdevices.databinding.ItemDeviceCardBinding
import com.example.a1valetdevices.interfaces.DeviceInteractionListener
import com.example.a1valetdevices.models.Device
import com.example.a1valetdevices.ui.viewholders.DevicesListViewHolder

class DevicesListAdapter(val devicesList: List<Device>,
                         val callback: DeviceInteractionListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DevicesListViewHolder(
            ItemDeviceCardBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false),
            callback
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as DevicesListViewHolder).bind(devicesList[position])

    override fun getItemCount(): Int = devicesList.size
}