package com.geekbrains.nasalibkotlin.view.currentphoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.geekbrains.nasalibkotlin.R
import com.geekbrains.nasalibkotlin.databinding.FragmentCPBinding
import com.geekbrains.nasalibkotlin.model.entity.Element
import com.geekbrains.nasalibkotlin.utils.ImageSetter

class CurrentPhotoFragment : Fragment() {

    private var _binding: FragmentCPBinding? = null
    private val binding get() = _binding!!
    private lateinit var element: Element
    private lateinit var imageSetter: ImageSetter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCPBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        imageSetter = ImageSetter()
        element = arguments?.getSerializable(getString(R.string.serializable_key_for_CP)) as Element
        setData(element)
    }

    private fun setData(element: Element){
        (activity as AppCompatActivity).supportActionBar?.title = element.title
        var creator = getString(R.string.unknown)
        if(!element.creator.isNullOrEmpty()) creator = element.creator!!
        imageSetter.setImage(element.URL, binding.currentPhotoIV)
        binding.CPTitleTV.text = String.format("%s%s", getString(R.string.CP_title), element.title)
        binding.CPCreatorTV.text = String.format("%s%s", getString(R.string.sec_creator), creator)
        binding.CPDateTV.text = String.format("%s%s", getString(R.string.date_creation), element.date)
    }

    private fun initToolbar(){
        (activity as AppCompatActivity).setSupportActionBar(binding.cpToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}