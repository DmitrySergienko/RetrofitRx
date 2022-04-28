package com.example.retrofitrx.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitrx.domain.GitProjectEntity
import com.example.retrofitrx.domain.ProjectsRepo
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class ReposViewModel(
    private val gitProjectRepo: ProjectsRepo
) : ViewModel() {
    private val _repos = MutableLiveData<List<GitProjectEntity>>()
    val repos: LiveData<List<GitProjectEntity>> = _repos

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun onShowRepos(username: String) {
        _inProgress.postValue(true)
        compositeDisposable.add(
            gitProjectRepo
                .observeReposForUser(username)
                .subscribeBy(
                    onSuccess = {
                        _inProgress.postValue(false)
                        _repos.postValue(it)
                    },
                    onError = {
                        // todo
                    }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}