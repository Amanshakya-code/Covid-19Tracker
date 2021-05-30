package com.example.covid_19tracker.mvvm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewmodelProviderFactory(val app: Application, private val repository: repository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Viewmodel(app,repository) as T


    }
}