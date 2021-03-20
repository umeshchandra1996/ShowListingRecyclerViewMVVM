
package com.example.photolisting.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.photolisting.R
import com.example.photolisting.databinding.ActivitySinglePhotoShowBinding

class SinglePhotoShow : AppCompatActivity() {
    lateinit var binding:ActivitySinglePhotoShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_single_photo_show)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_single_photo_show)
    }
}