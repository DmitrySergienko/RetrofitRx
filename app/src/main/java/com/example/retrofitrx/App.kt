package com.example.retrofitrx

import android.app.Application
import android.content.Context
import com.example.retrofitrx.di.AppDependenciesComponent
import com.example.retrofitrx.di.AppDependenciesModule
import com.example.retrofitrx.di.DaggerAppDependenciesComponent

class App : Application() {
    lateinit var appDependenciesComponent: AppDependenciesComponent

    override fun onCreate() {
        super.onCreate()
        appDependenciesComponent = DaggerAppDependenciesComponent
            .builder()
            .appDependenciesModule(AppDependenciesModule(this))
            .build()
    }

}

val Context.app: App
    get() {
        return applicationContext as App
    }