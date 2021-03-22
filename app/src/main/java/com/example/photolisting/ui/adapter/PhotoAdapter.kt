package com.example.photolisting.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photolisting.R
import com.example.photolisting.databinding.PhotoListLayoutBinding
import com.example.photolisting.model.PhotoListingResponces


class PhotoAdapter(var list:List<PhotoListingResponces>,val mlistener:onItemClickListner) : RecyclerView.Adapter<PhotoViewHolder>() {

    interface onItemClickListner{
        fun onItemClick(listdata:PhotoListingResponces)
    }
    lateinit var binding: PhotoListLayoutBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.photo_list_layout, parent, false
        )
        return PhotoViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        if (!list.isNullOrEmpty()) {
            var listResponse = list.get(position)
            println("_____${listResponse}")
            holder.bind(listResponse, listResponse.downloadUrl, listResponse.author, onItemClickListner = mlistener)

        }
    }
        override fun getItemCount(): Int {
            if (!list.isNullOrEmpty()) {
                return list.size
            } else {
                return 0
            }
        }


    }


    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(listDataonPosition:PhotoListingResponces, imageUrl: String, text: String,  onItemClickListner: PhotoAdapter.onItemClickListner) {
        var imageView:ImageView=itemView.findViewById(R.id.iv_photo_album)
        var nametext:TextView=itemView.findViewById(R.id.tv_photoAlbumName)
        nametext.text=text
        Glide.with(itemView.context)
                .load(imageUrl)
                .into(imageView)
        itemView.setOnClickListener{
        onItemClickListner.onItemClick(listDataonPosition)

        }
    }
}





