package com.juancarlos.retrofitandcoroutines.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juancarlos.retrofitandcoroutines.Constants
import com.juancarlos.retrofitandcoroutines.R
import com.juancarlos.retrofitandcoroutines.data.PhotosRepository
import com.juancarlos.retrofitandcoroutines.data.model.PhotosModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(private val repository: PhotosRepository): ViewModel() {


    private val photosList = MutableLiveData<List<PhotosModel>>()
    private val loadStatus = MutableLiveData(0)

    fun getData() {
        loadStatus.value = Constants.STATUS_LOADING
        GlobalScope.launch {
            try {
                val userData = repository.getPhotosList()
                photosList.postValue(userData)
                loadStatus.postValue(Constants.STATUS_COMPLETE)
            } catch (e: Exception) {
                e.printStackTrace()
                loadStatus.postValue(Constants.STATUS_ERROR)
            }
        }

    }

    fun getPhotoData(): LiveData<List<PhotosModel>> = photosList

    fun getLoadingStatus(): LiveData<Int> = loadStatus
}
