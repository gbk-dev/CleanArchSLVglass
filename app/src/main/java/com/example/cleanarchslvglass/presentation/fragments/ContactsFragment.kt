package com.example.cleanarchslvglass.presentation.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchslvglass.R
import com.example.cleanarchslvglass.databinding.FragmentContactsBinding
import com.example.cleanarchslvglass.databinding.FragmentGlassBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.internal.MapKitBinding
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.ui_view.ViewProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ContactsFragment : Fragment() {

    private var _binding : FragmentContactsBinding? = null
    private val binding get() = _binding!!
    private lateinit var map: MapView
    private var initialized: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialization()
    }

    private fun initialization(){
        when(initialized){
            true -> {

            }

            false -> {
                runCatching {
                    MapKitFactory.setApiKey("d6fdf527-1169-4b4b-8c93-0ff0f26b9056")
                }
                MapKitFactory.initialize(this.context)
                initialized = true
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(layoutInflater)
        val view = binding.root
        binding.scrollContacts.visibility = View.VISIBLE

        map = binding.mapView
        map.map.move(CameraPosition
            (Point(53.333564, 55.888757), 16.5f, 0.0f, 0.0f))

        val mapIcon = View(requireContext()).apply {
            background = requireContext().getDrawable(R.drawable.ic_map_icon)
        }

        map.map.mapObjects.addPlacemark((Point(53.333564, 55.888757)), ViewProvider(mapIcon))

        val toolbar = binding.contactsToolbar
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.frameContacts, SettingsFragment()).commitAllowingStateLoss()
            binding.scrollContacts.visibility = View.INVISIBLE
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        map.onStart()
    }

    override fun onStop() {
        map.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}