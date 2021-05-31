package com.nurhaqhalim.gitsterz.view.tablayout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nurhaqhalim.gitsterz.core.domain.model.UserModel
import com.nurhaqhalim.gitsterz.databinding.ListReposBinding

class TabsMainAdapter : RecyclerView.Adapter<TabsMainAdapter.ReposViewHolder>() {
    private val result = ArrayList<UserModel>()
    fun setData(items: List<UserModel>) {
        result.clear()
        result.addAll(items)
        notifyDataSetChanged()
    }

    class ReposViewHolder(val binding: ListReposBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder =
        ReposViewHolder(
            ListReposBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        val user = result[position]
        if (user.name != ""){
            val image = "@drawable/documents"
            val source = holder.binding.root.resources.getIdentifier(image, "drawable", holder.binding.root.context.packageName)
            holder.binding.repoName.text = user.name
            Glide.with(holder.binding.root)
                .load(user.avatar_url)
                .placeholder(source)
                .apply(RequestOptions().override(55,55))
                .into(holder.binding.ava)}
        else{
            holder.binding.repoName.text = user.login
            Glide.with(holder.binding.root)
                .load(user.avatar_url)
                .apply(RequestOptions().override(55,55))
                .into(holder.binding.ava)}

    }

    override fun getItemCount(): Int = result.size
}