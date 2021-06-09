package com.nurhaqhalim.gitsterz.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurhaqhalim.gitsterz.core.domain.model.UserModel
import com.nurhaqhalim.gitsterz.favorite.databinding.ActivityFavoritesBinding
import com.nurhaqhalim.gitsterz.favorite.di.favoriteModule
import com.nurhaqhalim.gitsterz.view.detail.DetailActivity
import com.nurhaqhalim.gitsterz.view.adapter.GitsterzAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

@ExperimentalCoroutinesApi
class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritesBinding
    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var favAdapter: GitsterzAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        showLoading(true)
        favAdapter = GitsterzAdapter()

        binding.listFav.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = favAdapter
        }

        viewModel.getFavoriteList().observe(this,{
            showLoading(false)
            if (it.isNotEmpty()){
                favAdapter.setData(it)
            }else{
                favAdapter.setData(it)
            }
        })

        favAdapter.setOnItemClickCallback(object: GitsterzAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserModel) {
                Intent(this@FavoritesActivity, DetailActivity::class.java).also{
                    it.putExtra(DetailActivity.EXTRA_DATA,data)
                    startActivity(it)
                }
            }
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