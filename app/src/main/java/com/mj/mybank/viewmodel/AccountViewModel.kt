package com.mj.mybank.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mj.mybank.Account
import com.mj.mybank.AccountDatabase
import com.mj.mybank.AccountRepository


class AccountViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AccountRepository = AccountRepository(AccountDatabase.getDatabase(application).accountDao())
    val _accounts = MutableLiveData<List<Account>>()

    init {
        viewModelScope.launch {
            val allAccounts = repository.getAllAccounts().asLiveData()
            allAccounts.observeForever { accounts ->
                _accounts.value = accounts
            }
        }
    }

    fun insert(account: Account) = viewModelScope.launch {
        repository.insertAccount(account)
    }

    fun delete(account: Account) = viewModelScope.launch {
        repository.deleteAccount(account)
    }
}


