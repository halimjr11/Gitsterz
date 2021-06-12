package com.nurhaqhalim.gitsterz.view.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurhaqhalim.gitsterz.R
import com.nurhaqhalim.gitsterz.databinding.ActivityMainBinding
import com.nurhaqhalim.gitsterz.core.domain.model.UserModel
import com.nurhaqhalim.gitsterz.view.detail.DetailActivity
import com.nurhaqhalim.gitsterz.view.adapter.GitsterzAdapter
import com.nurhaqhalim.gitsterz.view.settings.SettingsActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var adapter: GitsterzAdapter
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

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


        adapter.setOnItemClickCallback(object : GitsterzAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserModel) {
                showSelected(data)
            }
        })

        mainViewModel.getAllData().observe(this,{
            showLoading(false)
            if (it.isEmpty()){
                    binding.recyclerView.visibility = View.GONE
                    binding.notFound.visibility = View.VISIBLE
                }else{
                    adapter.setData(it)
                }
            })


    }

    private fun showSelected(data: UserModel){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA,data)
        startActivity(intent)
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressbar1.visibility = View.VISIBLE
        }else{
            binding.progressbar1.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                showLoading(true)
                binding.recyclerView.visibility = View.GONE
                mainViewModel.getSearchQuery(query).observe(this@MainActivity,{
                    showLoading(false)
                    if (it.isNotEmpty()){
                        binding.recyclerView.visibility = View.VISIBLE
                        adapter.setData(it)
                        adapter.notifyDataSetChanged()
                    }else{
                        binding.recyclerView.visibility = View.GONE
                        binding.notFound.visibility = View.VISIBLE
                    }
                })
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                val mIntent = Intent(this, SettingsActivity::class.java)
                startActivity(mIntent)
            }
            R.id.favorites -> {
                val uri = Uri.parse("gitsterz://favorites")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}