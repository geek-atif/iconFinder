package com.squareboat.iconfinder.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareboat.iconfinder.R
import com.squareboat.iconfinder.model.Icon
import com.squareboat.iconfinder.utils.Utils.Companion.askForPermission
import com.squareboat.iconfinder.utils.Utils.Companion.downloadImage
import com.squareboat.iconfinder.utils.Utils.Companion.isPermissionGranted
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_item.view.*

/**
 * Created by Atif Qamar on 26-11-2020.
 */

class IconAdapter(var list: List<Icon>) : RecyclerView.Adapter<IconAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun submitList(list: List<Icon>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            val item = list[position]
            with(itemView) {
                if (item.raster_sizes[0].formats[0].preview_url.isNullOrEmpty())
                    downloadRl.visibility = View.GONE
                Picasso.get()
                    .load(item.raster_sizes[0].formats[0].preview_url)
                    .placeholder(R.drawable.iconfinder)
                    .error(R.drawable.iconfinder)
                    .into(image)
                setButtonClick(
                    item.raster_sizes[0].formats[0].preview_url,
                    item.raster_sizes[0].formats[0].format
                )
            }
        }

        private fun View.setButtonClick(imageURL: String, fileFormat: String) {
            downloadRl.setOnClickListener {
                if (!isPermissionGranted(context)) {
                    askForPermission(context as Activity)
                } else {
                    downloadImage(imageURL, fileFormat, context)
                }
            }
        }
    }
}
