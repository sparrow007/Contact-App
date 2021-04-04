package com.jackandphantom.contactapp.di

import android.content.Context
import androidx.room.Room
import com.jackandphantom.contactapp.data.db.ContactDB
import com.jackandphantom.contactapp.data.repository.LocalRepository
import com.jackandphantom.contactapp.data.repository.Repository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun provideContactRepository(database: ContactDB, ioDispatcher: CoroutineDispatcher): LocalRepository {
        return LocalRepository(database.contactDao(), ioDispatcher)
    }


    @Singleton
    @Provides
    fun provideRoomDatabase(context : Context) : ContactDB {
        return Room.databaseBuilder(
            context.applicationContext,
            ContactDB::class.java,
            "Contact Database").build()

    }

    @Singleton
    @Provides
    fun provideDispatcher() = Dispatchers.IO

}