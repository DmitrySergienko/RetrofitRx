package com.example.retrofitrx.di

import com.example.retrofitrx.domain.ProjectsRepo
import com.example.retrofitrx.ui.ReposActivity
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppDependenciesModule::class
    ]
)
interface AppDependenciesComponent {
    fun inject(reposActivity: ReposActivity)
    @Named("username")
    fun getDefaultUserName(): String
    fun getProjectsRepo(): ProjectsRepo
}