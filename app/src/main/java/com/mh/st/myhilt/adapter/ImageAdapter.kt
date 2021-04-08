package com.mh.st.myhilt.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mh.st.myhilt.repository.datasource.local.SearchEntity

class ImageAdapter: PagingDataAdapter<SearchEntity, ImageHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(parent)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<SearchEntity>() {
    override fun areItemsTheSame(oldItem: SearchEntity, newItem: SearchEntity): Boolean =
        oldItem.thumbnail_url == newItem.thumbnail_url

    override fun areContentsTheSame(oldItem: SearchEntity, newItem: SearchEntity): Boolean =
        oldItem == newItem
}