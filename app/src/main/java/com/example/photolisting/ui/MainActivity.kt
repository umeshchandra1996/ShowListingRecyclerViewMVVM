package com.example.photolisting.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.photolisting.R
import com.example.photolisting.databinding.ActivityMainBinding
import com.example.photolisting.model.PhotoListingDataResponseItem
import com.example.photolisting.repository.PhotoListingRepository
import com.example.photolisting.resource.Status
import com.example.photolisting.ui.adapter.PhotoAdapter
import com.example.photolisting.viewModel.PhotoViewModel
import com.example.photolisting.viewModel.viewModelProvider


class MainActivity : AppCompatActivity(), PhotoAdapter.onItemClickListner {
    lateinit var adapter: PhotoAdapter
    lateinit var binding: ActivityMainBinding
    var repository: PhotoListingRepository = PhotoListingRepository()
    var listOfResponse: List<PhotoListingDataResponseItem> = listOf()
    lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModelSetup()
        recyclerViewSetup()
    }

    private fun recyclerViewSetup() {
        gridLayoutManager = GridLayoutManager(this, 2)
        adapter = PhotoAdapter(listOfResponse, this)
        binding.rvPhoto.adapter = adapter
        binding.rvPhoto.layoutManager = gridLayoutManager
    }

    private fun viewModelSetup() {
        val viewModelProviderFactory = viewModelProvider(repository = repository)
        val viewModel = ViewModelProvider(this, viewModelProviderFactory).get(PhotoViewModel::class.java)
        viewModel._liveData.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {

                    binding.progressCircular.visibility=View.GONE
                    if (it.data!!.isSuccessful) {
                        listOfResponse = it.data.body()!!
                        adapter.list = listOfResponse
                        adapter.notifyDataSetChanged()
                    }
                }
                Status.LOADING -> {
                    binding.progressCircular.visibility=View.VISIBLE
                }

            }
        }
    }

    override fun onItemClick(listdata: PhotoListingDataResponseItem) {
        val intent = Intent(this, SinglePhotoShow::class.java)
        intent.putExtra("ImageUrl", listdata.download_url)
        intent.putExtra("NameAuthor", listdata.author)
        startActivity(intent)
    }
}