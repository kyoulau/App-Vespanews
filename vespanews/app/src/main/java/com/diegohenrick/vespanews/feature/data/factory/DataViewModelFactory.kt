package com.diegohenrick.vespanews.feature.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diegohenrick.vespanews.feature.data.local.viewModel.DataViewModel

class DataViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom
                (DataViewModel::class.java)) {
            DataViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}