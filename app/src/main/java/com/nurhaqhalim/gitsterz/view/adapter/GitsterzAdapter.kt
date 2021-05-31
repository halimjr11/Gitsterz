package com.nurhaqhalim.gitsterz.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nurhaqhalim.gitsterz.core.databinding.ListUsersBinding
import com.nurhaqhalim.gitsterz.core.domain.model.UserModel

class GitsterzAdapter : RecyclerView.Adapter<GitsterzAdapter.ViewHolder>() {
    private val result = ArrayList<UserModel>()
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setData(items: List<UserModel>) {
        result.clear()
        result.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
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
        holder.binding.root.setOnClickListener{
            onItemClickCallback.onItemClicked(result[holder.bindingAdapterPosition])
        }
    }

    override fun getItemCount(): Int {
       return result.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: UserModel)
    }
}