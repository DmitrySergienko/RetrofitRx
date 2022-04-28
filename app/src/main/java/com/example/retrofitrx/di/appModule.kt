package com.example.retrofitrx.di

import com.example.retrofitrx.data.retrofit.GitHubApi
import com.example.retrofitrx.data.retrofit.RetrofitProjectsRepoImpl
import com.example.retrofitrx.domain.ProjectsRepo
import com.example.retrofitrx.ui.ReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<String>(named("api_url")) { "https://api.github.com/" }
    single<String>(named("default_user")) { "borhammere" }
    single<ProjectsRepo> { RetrofitProjectsRepoImpl(get()) }
    single<GitHubApi> { get<Retrofit>().create(GitHubApi::class.java) }
    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("api_url")))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(get())
            .build()
    }
    factory<Converter.Factory> { GsonConverterFactory.create() }

    viewModel { ReposViewModel(get()) }
}