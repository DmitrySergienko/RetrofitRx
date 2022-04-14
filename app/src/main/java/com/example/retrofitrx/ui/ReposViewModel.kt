package com.example.retrofitrx.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitrx.domain.GitProjectEntity
import com.example.retrofitrx.domain.ProjectsRepo
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class ReposViewModel(private val gitProjectRepo: ProjectsRepo) : ViewModel() {
    private val _repos = MutableLiveData<List<GitProjectEntity>>()
    val repos: LiveData<List<GitProjectEntity>> = _repos
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun onShowRepos(username: String) {
        compositeDisposable.add(
            gitProjectRepo
                .observeReposForUser(username)
                .subscribeBy {
                    _repos.postValue(it)
                }
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}