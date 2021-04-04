package com.jackandphantom.contactapp.contactdetails.di

import androidx.lifecycle.ViewModel
import com.jackandphantom.contactapp.contactdetails.ContactDetailsViewModel
import com.jackandphantom.contactapp.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ContactDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContactDetailsViewModel::class)
    abstract fun provideContactDetailsViewModel(contactDetailsViewModel: ContactDetailsViewModel): ViewModel
}