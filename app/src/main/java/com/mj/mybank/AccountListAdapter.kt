package com.mj.mybank

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AccountListAdapter(private val context: Context, private val accounts: List<Account>) :
    RecyclerView.Adapter<AccountListAdapter.ViewHolder>() {

    private var accountList: List<Account> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_account, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val account = accounts[position]

        holder.tvAccountName.text = account.name
        holder.tvAccountBalance.text = account.balance.toString()

        holder.cardView.setOnClickListener {
            val intent = Intent(context, AccountDetailActivity::class.java)
            intent.putExtra("account", account)
            context.startActivity(intent)
        }
    }

    fun setData(accounts: List<Account>?) {
        accountList = accounts ?: emptyList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = accounts.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAccountName: TextView = itemView.findViewById(R.id.account_name_textview)
        val tvAccountBalance: TextView = itemView.findViewById(R.id.account_balance_textview)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}