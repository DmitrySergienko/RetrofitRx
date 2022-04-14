package com.example.retrofitrx.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitrx.app
import com.example.retrofitrx.databinding.ActivityReposBinding

class ReposActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReposBinding
    private val viewModel: ReposViewModel by viewModels { ReposViewModelFactory(app.gitProjectsRepo) }
    private val adapter = GitProjectsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReposBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initOutgoingEvents()
        initIncomingEvents()
    }

    private fun initViews() {
        binding.reposRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setHasStableIds(true)
        binding.reposRecyclerView.adapter = adapter
    }

    private fun initOutgoingEvents() {
        binding.showButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            viewModel.onShowRepos(username)
        }
    }

    private fun initIncomingEvents() {
        viewModel.repos.observe(this) {
            adapter.setData(it)
        }
    }

}