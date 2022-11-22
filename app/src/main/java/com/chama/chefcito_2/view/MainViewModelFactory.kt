package com.chama.chefcito_2.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chama.chefcito_2.repository.Repository

class MainViewModelFactory (
    private val repository: Repository
    ) : ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }