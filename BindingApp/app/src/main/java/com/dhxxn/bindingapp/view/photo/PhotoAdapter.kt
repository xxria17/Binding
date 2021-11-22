package com.dhxxn.bindingapp.view.photo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dhxxn.bindingapp.R
import com.dhxxn.bindingapp.data.entity.Gallery

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    private val resultList = mutableListOf<Gallery>()
    var onClick: (Gallery) -> Unit = {}

    inner class ViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        private val img: ImageView = view!!.findViewById<ImageView>(R.id.item_img)
        private val size = getDeviceSize(view!!.context)
        fun bind(result: Gallery) {
            Glide.with(itemView)
                .load(result.uri)
                .override(size)
                .centerCrop()
                .into(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoAdapter.ViewHolder, position: Int) {
        val data = resultList[position]
        holder.bind(data)
        holder.itemView.setOnClickListener { onClick(data) }
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    fun addItems(resultList: List<Gallery>) {
        this.resultList.clear()
        this.resultList.addAll(resultList)
        this.notifyDataSetChanged()
    }

    private fun getDeviceSize(context: Context): Int {
        val display = context.resources.displayMetrics
        return (display.widthPixels / 4)
    }
}