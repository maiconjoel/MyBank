package com.mj.mybank

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mj.mybank.databinding.ActivityAddAccountBinding
import com.mj.mybank.viewmodel.AddAccountViewModel

class AddAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddAccountBinding
    private lateinit var addAccountViewModel: AddAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Criar instância do AccountRepository
        val database = AccountDatabase.getDatabase(application)
        val repository = AccountRepository(database.accountDao())

        // Criar instância do AddAccountViewModel
        addAccountViewModel = ViewModelProvider(this, AddAccountViewModelFactory(repository))
            .get(AddAccountViewModel::class.java)

        binding.buttonSaveAccount.setOnClickListener {
            val name = binding.editTextAccountName.text.toString()
            val number = binding.editTextNumber.text.toString()
            val balance = binding.editTextBalance.text.toString().toDouble()

            val account = Account(name = name, number = number, balance = balance)

            addAccountViewModel.addAccount(account)

            val intent = Intent()
            intent.putExtra(EXTRA_ACCOUNT, account)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        const val EXTRA_ACCOUNT = "extra_account"
    }
}
