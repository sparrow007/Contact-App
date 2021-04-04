package com.jackandphantom.contactapp.di

import android.content.Context
import com.jackandphantom.contactapp.contactdetails.di.ContactDetailsComponent
import com.jackandphantom.contactapp.contactlist.di.ContactListComponent
import com.jackandphantom.contactapp.contactmanipulate.di.ContactManipulateComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton


@Component(modules = [AppModule::class, RepoModule::class, SubComponentModule::class,
    ViewModelFactoryModule::class])
@Singleton
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun contactListComponent(): ContactListComponent.Factory
    fun contactManipulateComponent(): ContactManipulateComponent.Factory
    fun contactDetailsComponent(): ContactDetailsComponent.Factory

}

@Module (
    subcomponents =[ContactListComponent::class, ContactManipulateComponent::class, ContactDetailsComponent::class]
)
object SubComponentModule