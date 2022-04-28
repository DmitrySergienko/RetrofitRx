package com.example.retrofitrx.data.retrofit

import com.example.retrofitrx.domain.GitProjectEntity
import com.example.retrofitrx.domain.ProjectsRepo
import io.reactivex.rxjava3.core.Single


class RetrofitProjectsRepoImpl(
    private val api: GitHubApi
) : ProjectsRepo {

    override fun observeReposForUser(username: String): Single<List<GitProjectEntity>> {
        return api.listRepos(username)
    }

}