package com.mj.mybank

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mj.mybank.viewmodel.AddAccountViewModel

class AddAccountViewModelFactory(private val repository: AccountRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddAccountViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddAccountViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


