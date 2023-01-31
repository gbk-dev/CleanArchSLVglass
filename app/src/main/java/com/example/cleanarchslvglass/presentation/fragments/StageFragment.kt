package com.example.cleanarchslvglass.presentation.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

import com.example.cleanarchslvglass.databinding.FragmentStageBinding

const val ARG_TEXT = "object"
const val ARG_BACKGROUND = "object1"

class StageFragment : Fragment() {

    private var _binding : FragmentStageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStageBinding.inflate(layoutInflater)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_TEXT)}?.apply {
            val textView = binding.textStage
            textView.text = getString(ARG_TEXT)
        }

        val const = binding.constFragStage
        val img = arguments?.getString(ARG_BACKGROUND)
        Glide.with(view.context).load(img).into(object : SimpleTarget<Drawable>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?
            ) {
                const.background = resource
            }
        })
    }
}