package com.example.a1valetdevices.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a1valetdevices.R
import com.example.a1valetdevices.databinding.FragmentHomeBinding
import com.example.a1valetdevices.interfaces.DeviceInteractionListener
import com.example.a1valetdevices.models.*
import com.example.a1valetdevices.ui.adapters.DevicesListAdapter
import com.example.a1valetdevices.ui.adapters.RecentReleasesAdapter
import com.example.a1valetdevices.ui.viewmodels.DevicesHomeViewModel
import com.example.a1valetdevices.util.toothpick.ActivityScope
import com.example.a1valetdevices.util.toothpick.ApplicationScope
import com.example.a1valetdevices.util.toothpick.ViewModelScope
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.viewmodel.closeOnViewModelCleared
import toothpick.smoothie.viewmodel.installViewModelBinding
import java.text.SimpleDateFormat
import java.util.*

class FragmentHome: Fragment(), DeviceInteractionListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    val viewModel: DevicesHomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeToothpick()
        setHasOptionsMenu(true)
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

    private fun setupObservers() {
        viewModel.deviceHomeState.observe(viewLifecycleOwner) {
            when (it) {
                is DeviceHomeStates.InitialState                ->  loadInitialState(it.devicesResponse)
                is DeviceHomeStates.DisplayQueryResultsState    ->  updateDevicesListAdapter(it.deviceList)
                is DeviceHomeStates.DisplayDeviceDetailsState   ->  displayDeviceDetails(it.device)
            }
        }

    }

    override fun displayDeviceDetails(device: Device) {
        val bundle = bundleOf(
            "name" to device.name,
            "os" to device.os,
            "platform" to device.platform.toString(),
            "size" to device.screenSize,
            "imageUrl" to device.imageUrl)
        findNavController().navigate(R.id.frag_home_to_details, bundle)
    }

    private fun loadInitialState(devicesResponse: DevicesResponse) {
        updateHomeScreenTexts(devicesResponse)
        devicesResponse.devices?.let {
            updateRecentReleasesView(it)
            updateDevicesListAdapter(it)
        }
    }


    private fun updateHomeScreenTexts(devicesLiveData: DevicesResponse?) {
        devicesLiveData?.let { deviceResponse ->
            deviceResponse.devices?.let { deviceList ->
                deviceList.map { it.releaseDate != 0L }
                binding.apply {
                    val androidCount = deviceList.filter { it.platform == Platform.ANDROID }.size
                    val iosCount = deviceList.filter { it.platform == Platform.IOS }.size
                    tvAndroidIosCount.text = requireContext().getString(R.string.fragment_home_info_both_platforms, androidCount.toString(), iosCount.toString())
                    tvTotalCount.text = requireContext().getString(R.string.fragment_home_info_total, deviceList.size.toString())
                }
            }
        }
    }

    private fun updateRecentReleasesView(devicesList: List<Device>) {
        devicesList.sortedByDescending { it.releaseDate }.take(3).let { recentDevices ->
            binding.ilRecentReleases.rvRecentReleases.apply {
                adapter = RecentReleasesAdapter(recentDevices, this@FragmentHome)
                layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
            }
        }
    }

    private fun updateDevicesListAdapter(devicesList: List<Device>?) {
        devicesList?.let {
            binding.rvDevicesList.apply {
                adapter = DevicesListAdapter(it, this@FragmentHome)
                layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
                setHasFixedSize(true)
            }
        }
    }

    private fun setupDate() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.format(Calendar.getInstance().time)
        binding.tvTitleTodaysDate.text = date
    }

    fun updateSearchResults(query: String) {
        viewModel.handle(Event.DevicceSearchQueried(query))
    }

}