package com.mj.mybank

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mj.mybank.databinding.ListItemAccountBinding

class AccountAdapter(private val onItemClick: (Account) -> Unit) : ListAdapter<Account, AccountAdapter.AccountViewHolder>(AccountDiffCallback()) {

    var accounts = emptyList<Account>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemAccountBinding.inflate(inflater, parent, false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val item = accounts[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = accounts.size

    inner class AccountViewHolder(private val binding: ListItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Account) {
            binding.accountNameTextview.text = item.name
            binding.accountBalanceTextview.text = item.balance.toString()
        }
    }
}

class AccountDiffCallback : DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem == newItem
    }
}
