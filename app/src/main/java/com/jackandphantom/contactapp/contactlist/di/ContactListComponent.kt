package com.jackandphantom.contactapp.contactlist.di

import com.jackandphantom.contactapp.contactlist.ContactList
import dagger.Subcomponent


@Subcomponent(modules = [ContactListModule::class])
interface ContactListComponent {

    fun inject(contactList: ContactList)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ContactListComponent
    }

}