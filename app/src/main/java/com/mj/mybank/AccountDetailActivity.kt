package com.mj.mybank

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.mj.mybank.databinding.ActivityAccountDetailBinding

class AccountDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountDetailBinding
    private lateinit var account: Account

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // obter a referência do botão
        val buttonBack: Button = findViewById(R.id.buttonBack)

        // adicionar o listener de clique
        buttonBack.setOnClickListener {
            // fechar a activity e voltar para a MainActivity
            finish()
        }

        // Obter a conta bancária selecionada da intent
        account = intent.getParcelableExtra("account")!!

        // Configura o título da action bar para o nome da account
        supportActionBar?.title = account.name

        // Configura o botão de voltar na action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Preencher os campos do layout com os dados da conta bancária
        binding.tvAccountName.text = account.name
        binding.tvAccountBalance.text = account.balance.toString()
        binding.tvAccountNumber.text = account.number
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
