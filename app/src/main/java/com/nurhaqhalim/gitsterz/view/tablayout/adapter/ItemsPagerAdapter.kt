package com.nurhaqhalim.gitsterz.view.tablayout.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nurhaqhalim.gitsterz.view.tablayout.TabsMainFragment

class ItemsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    var username:String = ""
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment = TabsMainFragment.newInstance(position, username)
}