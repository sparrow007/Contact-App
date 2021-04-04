package com.jackandphantom.contactapp.contactmanipulate.di

import androidx.lifecycle.ViewModel
import com.jackandphantom.contactapp.contactmanipulate.ContactManipulateViewModel
import com.jackandphantom.contactapp.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ContactManipulateModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContactManipulateViewModel::class)
    abstract fun provideContactManipulateViewModel(contactManipulateViewModel: ContactManipulateViewModel):ViewModel
}