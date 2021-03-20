package com.example.photolisting.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photolisting.R
import com.example.photolisting.databinding.ActivityMainBinding
import com.example.photolisting.model.PhotoListingResponces
import com.example.photolisting.repository.PhotoListingRepository
import com.example.photolisting.ui.adapter.PhotoAdapter
import com.example.photolisting.viewModel.PhotoViewModel
import com.example.photolisting.viewModel.viewModelProvider


class MainActivity : AppCompatActivity() {
    lateinit var gridLayoutManager:GridLayoutManager
    lateinit var adapter:PhotoAdapter
    lateinit var binding:ActivityMainBinding
    var repository:PhotoListingRepository= PhotoListingRepository()
    var listOfResponse: List<PhotoListingResponces> = listOf()

    //var viewModel:PhotoViewModel= PhotoViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModelSetup()
        recyclerViewSetup()
    }

    private fun recyclerViewSetup() {
       gridLayoutManager= GridLayoutManager(this,2)
        adapter= PhotoAdapter(this, listOfResponse)
        binding.rvPhoto.adapter=adapter
        binding.rvPhoto.layoutManager=gridLayoutManager
    }

    private fun viewModelSetup() {
        val viewModelProviderFactory = viewModelProvider(repository = repository)
        var viewModel = ViewModelProvider(this, viewModelProviderFactory).get(PhotoViewModel::class.java)
        //   viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(PhotoViewModel::class.java)

        viewModel.liveData.observe(this){

            listOfResponse=it
          //  adapter.list=listOfResponse
            adapter.notifyDataSetChanged()
        }
    }
}