package com.mj.mybank.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mj.mybank.Account
import com.mj.mybank.AccountRepository
import kotlinx.coroutines.launch

class AddAccountViewModel(private val repository: AccountRepository) : ViewModel() {

    private val _navigateBack = MutableLiveData<Unit>()
    val navigateBack: LiveData<Unit>
        get() = _navigateBack

    fun addAccount(account: Account) {
        viewModelScope.launch {
            repository.insertAccount(account)
            _navigateBack.value = Unit
        }
    }
}