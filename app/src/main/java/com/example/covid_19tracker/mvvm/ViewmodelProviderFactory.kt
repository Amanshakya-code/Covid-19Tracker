package com.example.covid_19tracker.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewmodelProviderFactory(private val repository: repository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Viewmodel(repository) as T


    }
}