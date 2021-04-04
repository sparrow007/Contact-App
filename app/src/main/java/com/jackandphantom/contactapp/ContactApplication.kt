package com.jackandphantom.contactapp

import android.app.Application
import com.jackandphantom.contactapp.di.AppComponent
import com.jackandphantom.contactapp.di.DaggerAppComponent

class ContactApplication: Application() {

    val appComponent :AppComponent by lazy {
        initialize()
    }

   private fun initialize(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}