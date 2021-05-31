package com.nurhaqhalim.gitsterz.view.tablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurhaqhalim.gitsterz.view.tablayout.adapter.TabsMainAdapter
import com.nurhaqhalim.gitsterz.databinding.FragmentMainTabsBinding
import com.nurhaqhalim.gitsterz.view.detail.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TabsMainFragment : Fragment() {
    private lateinit var adapter: TabsMainAdapter
    private lateinit var binding: FragmentMainTabsBinding
    private val viewModel: DetailViewModel by viewModel()
    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        const val USERNAME = "section_username"
        @JvmStatic
        fun newInstance(index: Int, username: String) =
            TabsMainFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                    putString(USERNAME, username)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainTabsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username1 = arguments?.getString(USERNAME)
        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        showLoading(true)
        adapter = TabsMainAdapter()
        binding.reposContainer.layoutManager = LinearLayoutManager(context)
        binding.reposContainer.adapter = adapter

        when(index){
            0 -> viewModel.getReposData(username1?: "").observe(viewLifecycleOwner,{
                if (it.isNotEmpty()){
                    adapter.setData(it)
                }
            })
            1 -> viewModel.getFollowersData(username1 ?: "").observe(viewLifecycleOwner,{
                if (it.isNotEmpty()){
                    adapter.setData(it)
                }
            })
            2 -> viewModel.getFollowingData(username1?: "").observe(viewLifecycleOwner,{
                if (it.isNotEmpty()){
                    adapter.setData(it)
                }
            })
        }
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressbar2.visibility = View.VISIBLE
        }else{
            binding.progressbar2.visibility = View.GONE
        }
    }
}