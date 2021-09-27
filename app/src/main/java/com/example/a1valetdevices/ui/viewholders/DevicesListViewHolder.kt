package com.example.a1valetdevices.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a1valetdevices.databinding.ItemDeviceCardBinding
import com.example.a1valetdevices.models.Device

class DevicesListViewHolder(val binding: ItemDeviceCardBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(device: Device) {
        binding.apply {
            device.name?.let { binding.tvDeviceTitle.text = it }
            device.name?.let { binding.tvDeviceStatus.text = it }
            device.imageUrl?.let {
                Glide.with(binding.root.context)
                    .load(it)
                    .into(binding.ivDevice)
            }
        }
    }
}