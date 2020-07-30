package com.msc.tikitest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules).androidContext(applicationContext)
        }
    }
}