package com.example.retrofitrx.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitrx.app
import com.example.retrofitrx.databinding.ActivityReposBinding
import com.example.retrofitrx.domain.ProjectsRepo
import javax.inject.Inject

class ReposActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReposBinding

    @Inject
    lateinit var gitProjectsRepo: ProjectsRepo

    private val viewModel: ReposViewModel by viewModels {
        ReposViewModelFactory(gitProjectsRepo)
    }
    private val adapter = GitProjectsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReposBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app.appDependenciesComponent.inject(this)

        initViews()
        initOutgoingEvents()
        initIncomingEvents()
    }

    private fun initViews() {
        binding.reposRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setHasStableIds(true)
        binding.reposRecyclerView.adapter = adapter
        binding.usernameEditText.setText(app.appDependenciesComponent.getDefaultUserName())
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
        viewModel.inProgress.observe(this) { inProgress ->
            binding.showButton.isEnabled = !inProgress
            binding.usernameEditText.isEnabled = !inProgress
            binding.progressBarLayout.isVisible = inProgress
        }
    }

}