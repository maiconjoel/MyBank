package com.mj.mybank

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mj.mybank.AddAccountActivity
import com.mj.mybank.viewmodel.AccountViewModel


class MainActivity : AppCompatActivity() {
    private var accounts: MutableList<Account> = mutableListOf(Account(1, "Conta 1", "1234", 100.0), Account(2, "Conta 2", "5678", 200.0)) // Aqui você deve obter a lista de contas do seu repositório
    private lateinit var accountListAdapter: AccountListAdapter
    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvAccounts: RecyclerView = findViewById(R.id.rvAccounts)
        rvAccounts.layoutManager = LinearLayoutManager(this)

        accountListAdapter = AccountListAdapter(this, accounts)
        rvAccounts.adapter = accountListAdapter

        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        accountViewModel._accounts.observe(this, Observer { accounts ->
            accountListAdapter.setData(accounts)
        })

        // Configurar o botão de adicionar conta
        val addAccountButton = findViewById<FloatingActionButton>(R.id.fabAddAccount)
        addAccountButton.setOnClickListener {
            val intent = Intent(this, AddAccountActivity::class.java)
            startActivityForResult(intent, ADD_ACCOUNT_REQUEST)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_ACCOUNT_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Account>(AddAccountActivity.EXTRA_ACCOUNT)?.let { account ->
                accounts.add(account)
                accountListAdapter.notifyDataSetChanged()
            }
        }
    }
    companion object {
        const val ADD_ACCOUNT_REQUEST = 1
    }
}
