package com.example.cleanarchslvglass.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleanarchslvglass.R
import com.example.cleanarchslvglass.databinding.FragmentAboutAppBinding

class AboutAppFragment : Fragment() {

    private var _binding : FragmentAboutAppBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutAppBinding.inflate(layoutInflater)
        binding.constAboutApp.visibility = View.VISIBLE
        val view = binding.root

        val version = 1
        val versionText = resources.getText(R.string.version)
        val build = 1
        val buildText = resources.getText(R.string.build)
        val intelligence = resources.getText(R.string.intelligence)
        val site = resources.getText(R.string.site)

        binding.textVersion.text = "$versionText $version $buildText $build"
        binding.aboutLink.text = "$intelligence\n$site"

        val toolbar = binding.aboutToolbar
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.frameAbout, SettingsFragment()).commit()
            binding.constAboutApp.visibility = View.INVISIBLE
        }

        return view
    }

}