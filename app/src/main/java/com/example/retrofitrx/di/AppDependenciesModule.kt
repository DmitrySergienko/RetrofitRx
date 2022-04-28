package com.example.retrofitrx.di

import android.content.Context
import com.example.retrofitrx.data.retrofit.GitHubApi
import com.example.retrofitrx.data.retrofit.RetrofitProjectsRepoImpl
import com.example.retrofitrx.domain.ProjectsRepo
import com.example.retrofitrx.ui.ReposViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppDependenciesModule(val context: Context) {

    @Singleton
    @Provides
    fun provideGitHubApi(retrofit: Retrofit): GitHubApi {
        return retrofit.create(GitHubApi::class.java)
    }

    @Singleton
    @Provides
    fun provideProjectRepo(api: GitHubApi): ProjectsRepo {
        return RetrofitProjectsRepoImpl(api)
    }

    @Provides
    @Named("username")
    fun provideDefaultUserName(): String {
        return "kshalnov"
    }

    @Provides
    @Named("api_url")
    fun provideBaseUrl(): String {
        return "https://api.github.com/"
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(@Named("api_url") baseUrl: String, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .build()
    }
}