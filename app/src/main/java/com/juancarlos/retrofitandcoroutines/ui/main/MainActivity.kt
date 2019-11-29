package com.juancarlos.retrofitandcoroutines.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.juancarlos.retrofitandcoroutines.Constants
import com.juancarlos.retrofitandcoroutines.adapters.PhotosAdapter
import com.juancarlos.retrofitandcoroutines.R
import com.juancarlos.retrofitandcoroutines.ui.TopSpacingItemDecoration
import com.juancarlos.retrofitandcoroutines.data.model.PhotosModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var model: MainActivityViewModel
    private lateinit var photosAdapter: PhotosAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var statusLoading: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProviders.of(this, MainViewModelFactory(application)).get(MainActivityViewModel::class.java)
        val topSpacingDecorator = TopSpacingItemDecoration(5)
        recycler_view.addItemDecoration(topSpacingDecorator)

        getloadingStatus()
        getData(statusLoading)

        // refresh your list contents somehow
        swLayout.setOnRefreshListener {
                getData(statusLoading)
                swLayout.isRefreshing = false
        }
    }

    private fun setRecyclerView(photoList: List<PhotosModel>) {
        val photoAdapter = PhotosAdapter(this, photoList)
        viewManager = LinearLayoutManager(applicationContext)
        recycler_view.apply {
            layoutManager = viewManager
            photosAdapter = photoAdapter
            adapter = photosAdapter
        }
        recycler_view.setHasFixedSize(true)
    }

    private fun getData(status:Int) {
        model.getData(status)
        model.getPhotoData().observe(this, Observer {
            if (it != null) {
                setRecyclerView(it)
            }
        })
    }


    private fun getloadingStatus() {
        model.getLoadingStatus().observe(this, Observer {
            Log.d("Inspection","status: $it ")
            when (it) {
                Constants.STATUS_COMPLETE -> {
                    pb_progress.visibility = View.GONE
                    statusLoading = Constants.STATUS_COMPLETE
                }
                Constants.STATUS_LOADING -> {
                    pb_progress.visibility = View.VISIBLE
                    statusLoading = Constants.STATUS_LOADING
                }
                Constants.STATUS_ERROR -> {
                    pb_progress.visibility = View.GONE
                    statusLoading = Constants.STATUS_ERROR
                    showSnackBar("¡Al parecer algo salió mal!")
                }
            }
        })
    }

    private fun showSnackBar(message: String) {
        Snackbar
            .make(cl_container, message, Snackbar.LENGTH_INDEFINITE)
            .setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
            .setAction("Volver intentarlo") {
                model.getData(statusLoading)
            }
            .show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
