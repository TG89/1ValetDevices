package com.example.a1valetdevices.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a1valetdevices.R
import com.example.a1valetdevices.databinding.FragmentHomeBinding
import com.example.a1valetdevices.models.Device
import com.example.a1valetdevices.models.DevicesResponse
import com.example.a1valetdevices.models.Platform
import com.example.a1valetdevices.ui.adapters.DevicesListAdapter
import com.example.a1valetdevices.ui.viewmodels.DevicesHomeViewModel
import com.example.a1valetdevices.util.hideKeyboard
import com.example.a1valetdevices.util.toothpick.ActivityScope
import com.example.a1valetdevices.util.toothpick.ApplicationScope
import com.example.a1valetdevices.util.toothpick.ViewModelScope
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.viewmodel.closeOnViewModelCleared
import toothpick.smoothie.viewmodel.installViewModelBinding
import java.text.SimpleDateFormat
import java.util.*

class FragmentHome: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    val viewModel: DevicesHomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeToothpick()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupDate()
    }

    private fun setupDate() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.format(Calendar.getInstance().time)
        binding.tvTitleTodaysDate.text = date
    }

    private fun setupObservers() {
        viewModel.deviceResponseLiveData.observe(viewLifecycleOwner) { devicesResponse ->
            updateHomeScreenTexts(devicesResponse)
            devicesResponse.devices?.let {
                updateRecentReleasesView(it)
                updateDevicesListAdapter(it)
            }
        }
    }

    private fun updateHomeScreenTexts(devicesLiveData: DevicesResponse?) {
        devicesLiveData?.let { deviceResponse ->
            deviceResponse.devices?.let { deviceList ->
                //                                          TODO REVISIT
                deviceList.map { it.releaseDate != 0L }
                binding.apply {
                    val androidCount = deviceList.filter { it.platform == Platform.ANDROID }.size
                    val iosCount = deviceList.filter { it.platform == Platform.IOS }.size
                    tvAndroidIosCount.text = requireContext().getString(R.string.fragment_home_info_both_platforms, androidCount.toString(), iosCount.toString())
                    tvTotalCount.text = deviceList.size.toString()
                }
            }
        }
    }

    private fun updateRecentReleasesView(devicesList: List<Device>) {
        //                                                  TODO REVISIT
        devicesList.map { it.releaseDate }
    }

    private fun updateDevicesListAdapter(devicesList: List<Device>?) {
        devicesList?.let {
            binding.rvDevicesList.apply {
                adapter = DevicesListAdapter(it)
                layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
                setHasFixedSize(true)
            }
        }
    }

    private fun initializeToothpick() {
        KTP
            .openRootScope()
            .openSubScope(ApplicationScope::class.java)
            .openSubScope(ActivityScope::class.java)
            .openSubScope(ViewModelScope::class.java){
                it.installViewModelBinding<DevicesHomeViewModel>(requireActivity())
                it.closeOnViewModelCleared(requireActivity())
            }
            .inject(this)
    }
}