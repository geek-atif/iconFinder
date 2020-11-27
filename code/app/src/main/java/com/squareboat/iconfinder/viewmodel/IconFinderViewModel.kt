package com.squareboat.iconfinder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareboat.iconfinder.data.repositorie.AppRepository
import com.squareboat.iconfinder.model.IconFinderResponse
import com.squareboat.iconfinder.utils.UIEventManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * Created by Atif Qamar on 26-11-2020.
 */

class IconFinderViewModel(private val eventManager: UIEventManager) : ViewModel() {
    private val repository = AppRepository()
    private val _iconFinderResponse = MutableLiveData<IconFinderResponse>()
    val iconFinderResponse: LiveData<IconFinderResponse>
        get() = _iconFinderResponse

    init {
        _iconFinderResponse.value
    }

    fun getIcons(
        iconName: String,
        numberOfIcons: Int
    ): Job = viewModelScope.launch {

        try {
            eventManager.showProgressBar()
            _iconFinderResponse.value = repository.getIcons(iconName, numberOfIcons)
            eventManager.hideProgressBar()
        } catch (e: IOException) {
            eventManager.showToast(e.message.toString())
            eventManager.hideProgressBar()
        } catch (e: Exception) {
            e.printStackTrace()
            eventManager.showToast("Exception")
            eventManager.hideProgressBar()
        }
    }
}