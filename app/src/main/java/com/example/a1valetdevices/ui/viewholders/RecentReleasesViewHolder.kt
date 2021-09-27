package com.example.a1valetdevices.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a1valetdevices.R
import com.example.a1valetdevices.databinding.ItemSingleDeviceBinding
import com.example.a1valetdevices.interfaces.DeviceInteractionListener
import com.example.a1valetdevices.models.Device

class RecentReleasesViewHolder(val binding: ItemSingleDeviceBinding,
                               val callback: DeviceInteractionListener): RecyclerView.ViewHolder(binding.root) {
    fun bind(device: Device) {
        binding.apply {
            device.name?.let { binding.tvDeviceName.text = it }
            Glide.with(binding.root.context)
                .load(device.imageUrl?: R.drawable.ic_baseline_phone_android_grey_24)
                .into(binding.ivImage)
            clSingleDevice.setOnClickListener {
                callback.displayDeviceDetails(device)
            }
        }
    }
}