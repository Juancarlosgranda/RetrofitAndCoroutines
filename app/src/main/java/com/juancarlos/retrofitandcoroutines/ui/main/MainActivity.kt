package com.juancarlos.retrofitandcoroutines.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
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

class MainActivity : AppCompatActivity() {
    private lateinit var model: MainActivityViewModel
    private lateinit var photosAdapter: PhotosAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProviders.of(this,
            MainViewModelFactory(application)
        ).get(MainActivityViewModel::class.java)
        getloadingStatus()
        getData()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

//        viewManager = LinearLayoutManager(applicationContext)
//        recycler_view.apply {
//            layoutManager = viewManager
//            val topSpacingDecorator = TopSpacingItemDecoration(5)
//            addItemDecoration(topSpacingDecorator)
//            photosAdapter = PhotosAdapter(context)
//            adapter = photosAdapter
//        }
//        model.getUsers().observe(this, Observer<List<PhotosModel>>{ photos ->
//            // update UI
//            photosAdapter.submitList(photos)
//        })

        return super.onCreateView(name, context, attrs)
    }

    private fun setRecyclerView(photoList: List<PhotosModel>) {
        val photoAdapter =
            PhotosAdapter(this, photoList)
        viewManager = LinearLayoutManager(applicationContext)
        recycler_view.apply {
            layoutManager = viewManager
            val topSpacingDecorator =
                TopSpacingItemDecoration(5)
            addItemDecoration(topSpacingDecorator)
            photosAdapter = photoAdapter
            adapter = photosAdapter
        }
//        recycler_view.setHasFixedSize(true)
    }

    private fun getData() {
        model.getData()
        model.getPhotoData().observe(this, Observer {
            if (it != null) {
                setRecyclerView(it)
            }
        })
    }


    private fun getloadingStatus() {
        model.getLoadingStatus().observe(this, Observer {
            when (it) {
                Constants.STATUS_COMPLETE -> {
                    pb_progress.visibility = View.GONE
                }
                Constants.STATUS_LOADING -> {
                    pb_progress.visibility = View.VISIBLE
                }
                Constants.STATUS_ERROR -> {
                    pb_progress.visibility = View.GONE
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
                model.getData()
            }
            .show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
