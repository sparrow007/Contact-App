package com.jackandphantom.contactapp.data.repository

import com.jackandphantom.contactapp.data.model.Contact
import com.jackandphantom.contactapp.data.Result

interface Repository {

    suspend fun insertContact(contact: Contact) : Result<Boolean>

    suspend fun deleteContact(id: Int) : Result<Boolean>

    suspend fun updateContact(contact: Contact) : Result<Boolean>

    suspend fun getContactList() : Result<List<Contact>>

    suspend fun getContact(id: Int): Result<Contact>

    suspend fun updateToFav(id: Int, fav: Boolean): Result<Boolean>

    suspend fun getFavContactList(): Result<List<Contact>>

}