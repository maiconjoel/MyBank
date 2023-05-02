package com.mj.mybank

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AccountRepository(private val accountDao: AccountDao) {
    suspend fun insertAccount(account: Account) {
        accountDao.insertAccount(account)
    }

    suspend fun updateAccount(account: Account) {
        accountDao.updateAccount(account)
    }

    suspend fun deleteAccount(account: Account) {
        accountDao.deleteAccount(account)
    }

    suspend fun getAllAccounts(): Flow<List<Account>> = accountDao.getAllAccounts()

    suspend fun getAccountById(id: Int): Account {
        return accountDao.getAccountById(id)
    }
}
