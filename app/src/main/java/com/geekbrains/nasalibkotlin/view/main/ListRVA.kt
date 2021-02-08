package com.geekbrains.nasalibkotlin.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.nasalibkotlin.R
import com.geekbrains.nasalibkotlin.model.entity.Element
import com.geekbrains.nasalibkotlin.utils.ImageSetter

class ListRVA() : RecyclerView.Adapter<ListRVA.ImageViewHolder>() {

    private val imageSetter : ImageSetter = ImageSetter()
    private var elements : List<Element?>? = null

    fun setMedia(elements: List<Element?>?){
        this.elements = elements
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val context = parent.context
        val layoutId: Int = R.layout.main_vh
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(layoutId, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        elements.let { holder.bind(position) }
    }

    override fun getItemCount(): Int {
        return if (elements != null) elements!!.size else 0
    }

    inner class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var imageView: ImageView = itemView.findViewById(R.id.previewIV)
        var element: Element? = null

        init {
            imageView.setOnClickListener { _ ->
                val bundle = bundleOf("CP" to element)
                imageView.findNavController().navigate(R.id.action_listFragment_to_currentPhotoFragment, bundle)
            }
        }

        fun bind(position: Int) {
            element = elements?.get(position)
            if (elements?.get(position)?.URL != null) {
                imageSetter.setImage(elements!![position]?.URL, imageView)
            }
        }
    }
}