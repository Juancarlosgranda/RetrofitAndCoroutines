package com.juancarlos.retrofitandcoroutines.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juancarlos.retrofitandcoroutines.data.PhotosDataSource
import com.juancarlos.retrofitandcoroutines.data.PhotosRepository

class MainViewModelFactory (private val application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(
                repository = PhotosRepository(
                    dataSource = PhotosDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}