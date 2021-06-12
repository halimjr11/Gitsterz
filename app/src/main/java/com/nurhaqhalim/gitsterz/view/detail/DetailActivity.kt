@file:Suppress("NAME_SHADOWING")

package com.nurhaqhalim.gitsterz.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.nurhaqhalim.gitsterz.core.domain.model.UserModel
import com.nurhaqhalim.gitsterz.R
import com.nurhaqhalim.gitsterz.databinding.ActivityDetailBinding
import com.nurhaqhalim.gitsterz.view.tablayout.adapter.ItemsPagerAdapter
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

@ExperimentalCoroutinesApi
class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private lateinit var spAdapter : ItemsPagerAdapter
    private val viewModel: DetailViewModel by viewModel()

    companion object{
        const val EXTRA_DATA = "extra_data"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)

        val data = intent.getParcelableExtra<UserModel>(EXTRA_DATA) as UserModel
        val userName = data.login
        val id = data.id

        getDetail(userName)


        var isChecked = false

        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkFav(id)
            Timber.tag("cek").d(count.toString())
            withContext(Dispatchers.Main){
                if (count > 0){
                    binding.toggleButton.isChecked = true
                    isChecked = true
                }else{
                    binding.toggleButton.isChecked = false
                    isChecked = false
                }
            }
        }

        binding.toggleButton.setOnClickListener{
            isChecked = !isChecked
            if (isChecked){
                viewModel.setFavorite(id)
            }else{
                viewModel.unSetFavorite(id)
            }
            binding.toggleButton.isChecked = isChecked
        }

        spAdapter = ItemsPagerAdapter(this)
        spAdapter.username = userName
        binding.viewPager.adapter = spAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        binding.detailToolbar.inflateMenu(R.menu.share_menu)
        binding.detailToolbar.setOnMenuItemClickListener { item ->
            val data = intent.getParcelableExtra<UserModel>(EXTRA_DATA) as UserModel
            val userName = data.login
            val userUrl = data.html_url

            if (item?.itemId == R.id.share) {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Here, I share you data from Gitsterz App, You can find ${userName}, \n link : $userUrl"
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
            true
        }
    }

    private fun getDetail(username: String){
        showLoading(false)
        viewModel.getDetail(username).observe(this, {
            println(it.toString())
            if (it != null){
                showResult(it)
            }
        })
    }
    private fun showResult(results: UserModel) {
        val image = results.avatar_url
        binding.usernameDetail.text = results.login
        binding.nameDetail.text = results.name
        binding.city.text = results.location
        binding.companyDetail.text = results.company

        Glide.with(binding.root).load(image).into(binding.avatarDetail)
    }
    private fun showLoading(state: Boolean){
        if (state){
            binding.progressbar1.visibility = View.VISIBLE
        }else{
            binding.progressbar1.visibility = View.GONE
        }
    }
}