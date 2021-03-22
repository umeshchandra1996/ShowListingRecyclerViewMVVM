package com.example.photolisting.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.photolisting.R
import com.example.photolisting.databinding.ActivityMainBinding
import com.example.photolisting.model.PhotoListingResponces
import com.example.photolisting.repository.PhotoListingRepository
import com.example.photolisting.ui.adapter.PhotoAdapter
import com.example.photolisting.viewModel.PhotoViewModel
import com.example.photolisting.viewModel.viewModelProvider


class MainActivity : AppCompatActivity(), PhotoAdapter.onItemClickListner {


    lateinit var adapter:PhotoAdapter
    lateinit var binding:ActivityMainBinding
    var repository:PhotoListingRepository= PhotoListingRepository()
    var listOfResponse: List<PhotoListingResponces> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModelSetup()
        recyclerViewSetup()
    }

    private fun recyclerViewSetup() {
        var gridLayoutManager= GridLayoutManager(this,2)
        adapter= PhotoAdapter(listOfResponse,this)
        binding.rvPhoto.adapter=adapter
        binding.rvPhoto.layoutManager=gridLayoutManager
    }

    private fun viewModelSetup() {
        val viewModelProviderFactory = viewModelProvider(repository = repository)
        var viewModel = ViewModelProvider(this, viewModelProviderFactory).get(PhotoViewModel::class.java)
         viewModel.liveData.observe(this){
            listOfResponse=it
            adapter.notifyDataSetChanged()
        }
    }

    override fun onItemClick(listDataonPosition: PhotoListingResponces) {
        var intent=Intent(this,SinglePhotoShow::class.java)
        intent.putExtra("ImageUrl",listDataonPosition.downloadUrl)
        intent.putExtra("NameAuthor",listDataonPosition.author)
        startActivity(intent)
    }
}