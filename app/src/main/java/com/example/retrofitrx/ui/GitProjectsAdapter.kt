package com.example.retrofitrx.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitrx.domain.GitProjectEntity

class GitProjectsAdapter : RecyclerView.Adapter<GitProjectVh>() {
    private var data: List<GitProjectEntity> = emptyList()

    fun setData(repos: List<GitProjectEntity>) {
        data = repos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitProjectVh {
        return GitProjectVh.create(parent)
    }

    override fun onBindViewHolder(holder: GitProjectVh, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(pos: Int): GitProjectEntity = data[pos]

    override fun getItemCount(): Int = data.size

}
