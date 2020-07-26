package com.mh.st.myhilt.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mh.st.myhilt.repository.model.Documents

class ImageAdapter: PagingDataAdapter<Documents, ImageHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(parent)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<Documents>() {
    override fun areItemsTheSame(oldItem: Documents, newItem: Documents): Boolean =
        oldItem.thumbnail_url == newItem.thumbnail_url

    override fun areContentsTheSame(oldItem: Documents, newItem: Documents): Boolean =
        oldItem == newItem
}