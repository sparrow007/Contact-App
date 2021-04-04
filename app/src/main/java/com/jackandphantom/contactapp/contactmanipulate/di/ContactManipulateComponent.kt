package com.jackandphantom.contactapp.contactmanipulate.di

import com.jackandphantom.contactapp.contactmanipulate.ContactManipulateFragment
import dagger.Subcomponent


@Subcomponent(modules = [ContactManipulateModule::class])
interface ContactManipulateComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ContactManipulateComponent
    }

    fun inject(contactManipulateFragment: ContactManipulateFragment)
}