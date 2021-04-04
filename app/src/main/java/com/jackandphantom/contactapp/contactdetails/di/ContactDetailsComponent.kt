package com.jackandphantom.contactapp.contactdetails.di

import com.jackandphantom.contactapp.contactdetails.ContactDetailsFragment

import dagger.Subcomponent


@Subcomponent(modules = [ContactDetailsModule::class])
interface ContactDetailsComponent {

    fun inject(contactDetailsFragment: ContactDetailsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ContactDetailsComponent
    }

}