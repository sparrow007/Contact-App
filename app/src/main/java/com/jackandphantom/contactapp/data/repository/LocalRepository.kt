package com.jackandphantom.contactapp.data.repository

import com.jackandphantom.contactapp.data.Result
import com.jackandphantom.contactapp.data.db.ContactDao
import com.jackandphantom.contactapp.data.model.Contact
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepository (
    private val contactDao: ContactDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : Repository {

    override suspend fun insertContact(contact: Contact): Result<Boolean> = withContext(dispatcher) {
        try {
            contactDao.insertContact(contact)
            Result.Success(true)
        }catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun deleteContact(id: Int): Result<Boolean> = withContext(dispatcher) {
        try {
            contactDao.deleteContact(id)
            Result.Success(true)
        }catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun updateContact(contact: Contact): Result<Boolean> = withContext(dispatcher) {
        try {
            contactDao.updateContact(contact)
            Result.Success(true)
        }catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getContactList(): Result<List<Contact>> = withContext(dispatcher) {
        try {
            val result = contactDao.getAllContact()
            Result.Success(result)
        }catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getContact(id: Int): Result<Contact>  = withContext(dispatcher) {
        try {
            val result = contactDao.getContact(id)
            Result.Success(result)
        }catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun updateToFav(id: Int, fav: Boolean): Result<Boolean> = withContext(dispatcher) {
        try {
            contactDao.update(id, fav)
            Result.Success(true)
        }catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getFavContactList(): Result<List<Contact>> = withContext(dispatcher) {
        try {
            val result = contactDao.getFavContactList()
            Result.Success(result)
        }catch (e : Exception) {
            Result.Error(e)
        }
    }

}