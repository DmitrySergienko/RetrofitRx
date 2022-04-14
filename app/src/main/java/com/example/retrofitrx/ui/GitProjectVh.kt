package com.example.retrofitrx.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitrx.databinding.ItemGitProjectBinding
import com.example.retrofitrx.domain.GitProjectEntity

class GitProjectVh(private val binding: ItemGitProjectBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): GitProjectVh {
            val inflater = LayoutInflater.from(parent.context)
            return GitProjectVh(ItemGitProjectBinding.inflate(inflater))
        }
    }

    fun bind(item: GitProjectEntity) {
        binding.itemGitRepoId.text = item.id.toString()
        binding.itemGitRepoName.text = item.name
    }
}