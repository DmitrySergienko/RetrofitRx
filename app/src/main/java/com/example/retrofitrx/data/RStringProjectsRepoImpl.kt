package com.example.retrofitrx.data

import android.content.Context
import com.example.retrofitrx.R
import com.example.retrofitrx.domain.GitProjectEntity
import com.example.retrofitrx.domain.ProjectsRepo
import io.reactivex.rxjava3.core.Single

class RStringProjectsRepoImpl(
    private val context: Context
) : ProjectsRepo {

    override fun observeReposForUser(username: String): Single<List<GitProjectEntity>> {
        val dummyList = listOf(
            GitProjectEntity(0, context.getString(R.string.username_0)),
            GitProjectEntity(1, context.getString(R.string.username_1)),
            GitProjectEntity(2, context.getString(R.string.username_2))
        )

        return Single.just(dummyList)
    }
}