package com.squareboat.iconfinder.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareboat.iconfinder.utils.UIEventManager
import com.squareboat.iconfinder.viewmodel.IconFinderViewModel

/**
 * Created by Atif Qamar on 26-11-2020.
 */

class IconFinderModelFactory(private val eventManager: UIEventManager) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IconFinderViewModel::class.java)) {
            return IconFinderViewModel(
                eventManager
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}