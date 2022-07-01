package com.example.baseapp.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseapp.databinding.RowItemUserBinding
import com.example.baseapp.db.entities.User

class UsersAdapter(private val usersList: List<User>, private val onItemClick: (User) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    inner class ViewHolder(val rowItemUserBinding: RowItemUserBinding) :
        RecyclerView.ViewHolder(rowItemUserBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        RowItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            rowItemUserBinding.apply {
                itemView.setOnClickListener {
                    onItemClick(usersList[position])
                }
            }
        }
    }

    override fun getItemCount() = usersList.size
}