package com.mh.st.myhilt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mh.st.myhilt.R
import com.mh.st.myhilt.repository.model.Documents
import kotlinx.android.synthetic.main.item_image.view.*

class ImageHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)) {

    fun bind(item: Documents) = with(itemView) {
        iv_image.load(item.thumbnail_url)
    }
}