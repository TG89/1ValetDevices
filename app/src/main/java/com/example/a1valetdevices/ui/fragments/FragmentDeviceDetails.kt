package com.example.a1valetdevices.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.a1valetdevices.R
import com.example.a1valetdevices.databinding.FragmentDeviceDetailsBinding

class FragmentDeviceDetails: Fragment() {

    private var _binding: FragmentDeviceDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeviceDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateDeviceDetailsViews()
    }

    private fun updateDeviceDetailsViews() {
        arguments?.let {

            binding.apply {
                tvTitleName.text = requireContext().getString(R.string.fragment_details_name, it.getString("name"))
                tvTitleOs.text = requireContext().getString(R.string.fragment_details_os, it.getString("os"))
                tvTitlePlatform.text = requireContext().getString(R.string.fragment_details_platform, it.getString("platform"))
                tvTitleSize.text = requireContext().getString(R.string.fragment_details_size, it.getString("size"))
                Glide.with(binding.root.context)
                    .load(it.getString("imageUrl")?: R.drawable.ic_baseline_phone_android_grey_24)
                    .into(binding.ivDevice)
            }
        }
    }
}