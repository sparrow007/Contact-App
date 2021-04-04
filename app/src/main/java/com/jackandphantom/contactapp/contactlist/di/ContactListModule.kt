package com.jackandphantom.contactapp.contactlist.di

import androidx.lifecycle.ViewModel
import com.jackandphantom.contactapp.contactlist.ContactListViewModel
import com.jackandphantom.contactapp.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ContactListModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContactListViewModel::class)
    abstract fun provideViewModel(contactListViewModel: ContactListViewModel):ViewModel
}