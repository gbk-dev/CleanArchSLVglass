package com.example.cleanarchslvglass.presentation.fragments

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.cleanarchslvglass.R
import com.example.cleanarchslvglass.databinding.FragmentGlassBinding
import com.example.cleanarchslvglass.presentation.products.GlassActivity
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class GlassFragment : Fragment() {

    private var _binding : FragmentGlassBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGlassBinding.inflate(layoutInflater)
        val view = binding.root

        val title = arguments?.getString("title")
        val img = arguments?.getString("img")
        val description = arguments?.getString("description")
        val size = arguments?.getString("size")
        val thickness = arguments?.getString("thickness")
        val usage = arguments?.getString("usage")

        val toolbar = binding.fragmentGlassToolbar
        toolbar.title = title
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        (activity as GlassActivity).setSupportActionBar(toolbar)
        val actionBar = (activity as GlassActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            val intent = Intent(view.context, GlassActivity::class.java)
            startActivity(intent)
        }

        val coordinator = binding.coordinatorGlass
        Glide.with(view.context).load(img).into(object:SimpleTarget<Drawable>(){
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                coordinator.background = resource
            }
        })

        binding.titleFragGlass.text = title
        binding.descriptionFragGlass.text = description
        binding.sizeFragGlass.text = size
        binding.thicknessFragGlass.text = thickness
        binding.usageFragGlass.text = usage

        val appBarLayout: AppBarLayout = binding.appBarLayout
        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val totalRange = appBarLayout.totalScrollRange
            val difference = totalRange - toolbar.height

            if (abs(verticalOffset) >= difference){
                toolbar.background = ColorDrawable(resources.getColor(R.color.dark_blue))
            } else {
                toolbar.background = ColorDrawable(Color.TRANSPARENT)
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        binding.managerButton.setOnClickListener {
            val contactsFragment = ContactsFragment()
            childFragmentManager.beginTransaction().replace(R.id.frameGlassFragment, contactsFragment).commitAllowingStateLoss()
            binding.coordinatorGlass.visibility = View.INVISIBLE
            binding.frameGlassFragment.background = resources.getDrawable(R.drawable.ic_main)
        }

        binding.openWebSite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.salstek.ru/?locale=ru"))
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}