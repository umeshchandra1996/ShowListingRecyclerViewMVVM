
package com.example.photolisting.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.photolisting.R
import com.example.photolisting.databinding.ActivitySinglePhotoShowBinding

class SinglePhotoShow : AppCompatActivity() {
    lateinit var binding:ActivitySinglePhotoShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_single_photo_show)

        getIntentData()

    }

    private fun getIntentData() {
       Glide.with(this)
               .load(intent.getStringExtra("ImageUrl"))
               .into(  binding.root.findViewById(R.id.iv_photo_album))
    var TextName:TextView=binding.root.findViewById(R.id.tv_photoAlbumName)
        TextName.text=intent.getStringExtra("NameAuthor")
    }
}