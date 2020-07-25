package com.mh.st.myhilt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mh.st.myhilt.repository.model.Documents

class ImageAdapter(val context: Context): RecyclerView.Adapter<ImageHolder>() {
    private var dataSet: List<Documents> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false)
        return ImageHolder(view)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    fun setData(data: List<Documents>) {
        this.dataSet = data
        notifyDataSetChanged()
    }
}