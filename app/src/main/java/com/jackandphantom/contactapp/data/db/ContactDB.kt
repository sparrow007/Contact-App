package com.jackandphantom.contactapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jackandphantom.contactapp.data.model.Contact


@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDB : RoomDatabase(){

    abstract fun contactDao(): ContactDao
}