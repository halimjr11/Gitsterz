package com.nurhaqhalim.favoriteApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurhaqhalim.favoriteApp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel: FavoriteViewModel
    private lateinit var adapter: GitsterzAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)
        adapter = GitsterzAdapter()
        adapter.notifyDataSetChanged()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewmodel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        viewmodel.setUserFavorite(this)

        viewmodel.getDataUserFav().observe(this, {
            adapter.setData(it)
            showLoading(false)
        })

        supportActionBar?.title= "Favorite's User"
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressbar1.visibility = View.VISIBLE
        }else{
            binding.progressbar1.visibility = View.GONE
        }
    }
}