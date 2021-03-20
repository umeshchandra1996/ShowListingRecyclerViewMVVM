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
import com.example.photolisting.ui.SinglePhotoShow

class PhotoAdapter(context: Context,list:List<PhotoListingResponces>) : RecyclerView.Adapter<PhotoViewHolder>() {
    lateinit var binding: PhotoListLayoutBinding
    var context=context
    var list=list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.photo_list_layout, parent, false
        )
        return PhotoViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        if(!list.isNullOrEmpty()){
        var listResponse:PhotoListingResponces=list.get(position)

        holder.itemView.findViewById<TextView>(R.id.tv_photoAlbumName).setText(listResponse.author)
        Glide.with(context)
            .load(listResponse.downloadUrl)
            .into(  holder.itemView.findViewById<ImageView>(R.id.iv_photo_album))

        }
        holder.itemView.setOnClickListener{
            var intent=Intent(context,SinglePhotoShow::class.java)
            intent.putExtra("data",list)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
    if(!list.isNullOrEmpty()){
        return list.size
    }else {
        return 0
    }
    }


}

class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /*fun bind(imageView: String?,text: String?) {
        var imageView:ImageView=itemView.findViewById(R.id.iv_photo_album)
        var text:TextView=itemView.findViewById(R.id.tv_photoAlbumName)



    }*/



}
