package com.nurhaqhalim.favoriteApp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nurhaqhalim.favoriteApp.databinding.ListUsersBinding

class GitsterzAdapter : RecyclerView.Adapter<GitsterzAdapter.ViewHolder>() {
    private val result = ArrayList<MainModel>()
    fun setData(items: List<MainModel>) {
        result.clear()
        result.addAll(items)
        notifyDataSetChanged()
    }


    class ViewHolder(val binding: ListUsersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = result[position]
        holder.binding.username.text = user.login

        val imageSource = user.avatar_url
        Glide.with(holder.binding.root)
            .load(imageSource)
            .apply(RequestOptions().override(55,55))
            .into(holder.binding.ava)
    }

    override fun getItemCount(): Int {
       return result.size
    }
}