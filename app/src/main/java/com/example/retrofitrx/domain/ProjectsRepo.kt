package com.example.retrofitrx.domain

import io.reactivex.rxjava3.core.Single

interface ProjectsRepo {
    // C(R)UD
    fun observeReposForUser(username: String): Single<List<GitProjectEntity>>
}