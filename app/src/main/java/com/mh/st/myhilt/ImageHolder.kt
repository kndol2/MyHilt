package com.mh.st.myhilt

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mh.st.myhilt.repository.model.Documents
import kotlinx.android.synthetic.main.item_image.view.*

class ImageHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(item: Documents) = with(itemView) {
        iv_image.load(item.thumbnail_url)
    }
}